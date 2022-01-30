package com.shamlou.data.repoImpl

import com.shamlou.bases.mapper.Mapper
import com.shamlou.bases.readWrite.Readable
import com.shamlou.bases.readWrite.Writable
import com.shamlou.data.model.posts.ResponsePostData
import com.shamlou.data.model.posts.ResponsePostsData
import com.shamlou.data.utility.CompatibleResponsePost.compatibalePostDataList
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.data.utility.PostRemoteFakes.listSampleResponsePostDomain
import com.shamlou.data.utility.ValidGetPostsResponse.validResponsePostsData
import com.shamlou.data.utility.ValidGetPostsResponse.validResponsePostsDomain
import com.shamlou.domain.model.posts.ResponsePostDomain
import com.shamlou.domain.model.posts.ResponsePostsDomain
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostsRepositoryImplUnitTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var repo: PostsRepositoryImpl

    @MockK(relaxed = true)
    lateinit var postsLocalReadable: Readable<Unit, ResponsePostsData>

    @MockK(relaxUnitFun = true)
    lateinit var postsLocalWritable: Writable<ResponsePostsData, Unit>

    @MockK(relaxed = true)
    lateinit var postsRemoteReadable: Readable<Unit, ResponsePostsData>

    @MockK(relaxed = true)
    lateinit var mapperPostDataToDomain: Mapper<ResponsePostData, ResponsePostDomain>

    private fun setUpStubs(){
        coEvery { mapperPostDataToDomain.map(any()) } returns listSampleResponsePostDomain.first()
        coEvery { postsLocalReadable.read(Unit) } returns ResponsePostsData(listOf())
        coEvery { postsLocalWritable.write(any()) } returns Unit
        coEvery { postsRemoteReadable.read(Unit) } returns ResponsePostsData(listOf())
    }
    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        repo = PostsRepositoryImpl(
            postsLocalReadable,
            postsLocalWritable,
            postsRemoteReadable,
            mapperPostDataToDomain
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun getAllPosts_shouldReturnEmpty_whenRemoteAndLocalReturnEmpty() = mainCoroutineRule.runBlockingTest {

        //given
        setUpStubs()
        //when
        val response = repo.getAllPosts(Unit).first()
        //then
        verify { mapperPostDataToDomain.map(any()) wasNot Called}
        Assert.assertEquals(response , ResponsePostsDomain(emptyList()))
    }
    @Test
    @ExperimentalCoroutinesApi
    fun getAllPosts_shouldReturnLocal_whenItWasAvailable() = mainCoroutineRule.runBlockingTest {

        //given
        val remoteResponse = ResponsePostsData(compatibalePostDataList)
        val localResponse = ResponsePostsData(validResponsePostsData)
        setUpStubs()
        validResponsePostsData.mapIndexed { index, item ->
            coEvery { mapperPostDataToDomain.map(item) } returns validResponsePostsDomain[index]
        }
        coEvery { postsLocalReadable.read(Unit) } returns localResponse
        coEvery { postsRemoteReadable.read(Unit) } returns remoteResponse
        //when
        val response = repo.getAllPosts(Unit).first()
        //then
        coVerifyOrder {
            validResponsePostsData.map {
                mapperPostDataToDomain.map(it)
            }
        }
        coVerify {
            postsLocalReadable.read(Unit)
            postsRemoteReadable.read(Unit) wasNot Called
        }
        Assert.assertEquals(response , ResponsePostsDomain(validResponsePostsDomain))
    }
    @Test
    @ExperimentalCoroutinesApi
    fun getAllPosts_shouldReturnRemote_whenLocalIsNotAvailable() = mainCoroutineRule.runBlockingTest {

        //given
        val localResponse = ResponsePostsData(emptyList())
        val remoteResponse = ResponsePostsData(validResponsePostsData)
        setUpStubs()
        validResponsePostsData.mapIndexed { index, item ->
            coEvery { mapperPostDataToDomain.map(item) } returns validResponsePostsDomain[index]
        }
        coEvery { postsLocalReadable.read(Unit) } returns localResponse
        coEvery { postsRemoteReadable.read(Unit) } returns remoteResponse
        //when
        val response = repo.getAllPosts(Unit).first()
        //then
        coVerifyOrder {
            validResponsePostsData.map {
                mapperPostDataToDomain.map(it)
            }
        }
        coVerify {
            postsLocalReadable.read(Unit)
            postsRemoteReadable.read(Unit)
        }
        Assert.assertEquals(response , ResponsePostsDomain(validResponsePostsDomain))
    }


}