package com.shamlou.data.mappers

import com.shamlou.bases.mapper.Mapper
import com.shamlou.data.mappers.posts.MapperPostDataToDomain
import com.shamlou.data.model.posts.ResponsePostData
import com.shamlou.data.utility.CompatibleResponsePost
import com.shamlou.data.utility.MainCoroutineRule
import com.shamlou.domain.model.posts.ResponsePostDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MapperPostDataToDomainUnitTest(){

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mapper : Mapper<ResponsePostData, ResponsePostDomain> = MapperPostDataToDomain()

    @Test
    @ExperimentalCoroutinesApi
    fun map_shouldReturnCompatibleResponseWhenValidIput() = mainCoroutineRule.runBlockingTest {
        //when
        val response = mapper.map(CompatibleResponsePost.compatibalePostData)
        //then
        Assert.assertEquals(response , CompatibleResponsePost.compatibalePostDomain)
    }
}