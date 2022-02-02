package com.shamlou.data_local.fileReader

import android.content.Context
import com.shamlou.data.utility.Fakes.sampleErrorText
import com.shamlou.data.utility.Fakes.validCitiyListResponseJson
import com.shamlou.data_local.utility.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.Exception

class ReadFileFromAssetsTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK(relaxed = true)
    lateinit var context : Context
    private lateinit var reader : ReadFileFromAssets

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        reader = ReadFileFromAssets(context)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun readFile_shouldCallAssetOpen() = mainCoroutineRule.runBlockingTest {

        //given
        every { context.assets.open(any()) } returns ByteArrayInputStream(validCitiyListResponseJson.toByteArray())
        //when
        val fileName = "test.txt"
        reader.readFile(fileName)
        //then
        verify { context.assets.open(eq(fileName)) }
    }
    @Test
    @ExperimentalCoroutinesApi
    fun readFile_shouldReturnValidResult() = mainCoroutineRule.runBlockingTest {

        //given
        every { context.assets.open(any()) } returns ByteArrayInputStream(validCitiyListResponseJson.toByteArray())
        //when
        val fileName = "test.txt"
        val result = reader.readFile(fileName)
        //then
        Assert.assertEquals(result , validCitiyListResponseJson)
    }
    @Test
    @ExperimentalCoroutinesApi
    fun readFile_shouldThrowValidException() = mainCoroutineRule.runBlockingTest {

        //given
        every { context.assets.open(any()) } throws IOException(sampleErrorText)

        // i had to do this instead of assertThrow
        // since it does not support suspend call
        try {
            //when
            val fileName = "test.txt"
             reader.readFile(fileName)
        }catch (e : Exception){
            //then
            Assert.assertEquals(e.message, IOException(sampleErrorText).message)
        }
    }
}