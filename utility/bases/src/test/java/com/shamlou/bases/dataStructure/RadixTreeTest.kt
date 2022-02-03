package com.shamlou.bases.dataStructure


import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * parameterized test for radix tree
 */
@RunWith(Parameterized::class)
class RadixTreeTest(private val given: ArrayList<String>,
                    private val prefix: String,
                    private val expectedResult: ArrayList<String>) {


    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "r" , arrayListOf("Republic", "Rumbak", "Ruvo")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "R" , arrayListOf("Republic", "Rumbak", "Ruvo")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Ru" , arrayListOf("Rumbak", "Ruvo")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Re" , arrayListOf("Republic")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Brü" , arrayListOf("Brüggen")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "brü" , arrayListOf("Brüggen")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "brüQ" , arrayListOf<String>()),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Brü" , arrayListOf("Brüggen")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "" , arrayListOf<String>()),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Pfaffenhofen" , arrayListOf("Pfaffenhofen")),
            arrayOf(arrayListOf("Kathmandu" , "Rumbak" , "Surtagān" , "Republic" , "Bellara" , "Pfaffenhofen" , "Brüggen" , "Ruvo"), "Teh" , arrayListOf<String>())
        )
    }

    @Test
    fun radixTreeEndToEnd() {

        val tree = RadixTree<String>()
        tree.insertAll(given.map { Item(it, it) })
        val result = tree.search(prefix)
        Assert.assertEquals(expectedResult ,result)
    }

}