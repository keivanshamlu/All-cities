package com.shamlou.data.utility

import com.github.javafaker.Faker
import com.shamlou.data.model.posts.ResponsePostData
import com.shamlou.domain.model.posts.ResponsePostDomain
import java.io.IOException

object PostRemoteFakes {
    private val faker = Faker()


    val listSampleResponsePostDomain = faker.getListSampleResponsePostDomain()
    val sampleResponsePostData = faker.getSampleResponsePostData()
    val compatibleListRemoteData = getListResponsePostCompatible()
    val exception = IOException(faker.lorem().sentence())
}

object ValidGetPostsResponse{
    val validGetPostsResponse = """
        [
          {
            "userId": 1,
            "id": 1,
            "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body": "quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas totam nnostrum rerum est autem sunt rem eveniet architecto"
          },
          {
            "userId": 1,
            "id": 2,
            "title": "qui est esse",
            "body": "est rerum tempore vitae nsequi sint nihil reprehenderit dolor beatae ea dolores neque nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis nqui aperiam non debitis possimus qui neque nisi nulla"
          }
        ]
    """.trimIndent()
    val validResponsePostsDomain = listOf(
        ResponsePostDomain(1,1,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit" , "quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas totam nnostrum rerum est autem sunt rem eveniet architecto"),
        ResponsePostDomain(2,1,"qui est esse" , "est rerum tempore vitae nsequi sint nihil reprehenderit dolor beatae ea dolores neque nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis nqui aperiam non debitis possimus qui neque nisi nulla")
    )
    val validResponsePostsData = listOf(
        ResponsePostData(1,1,"sunt aut facere repellat provident occaecati excepturi optio reprehenderit" , "quia et suscipit nsuscipit recusandae consequuntur expedita et cum nreprehenderit molestiae ut ut quas totam nnostrum rerum est autem sunt rem eveniet architecto"),
        ResponsePostData(2,1,"qui est esse" , "est rerum tempore vitae nsequi sint nihil reprehenderit dolor beatae ea dolores neque nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis nqui aperiam non debitis possimus qui neque nisi nulla")
    )

}

object CompatibleResponsePost {
    val compatibalePostDomain = ResponsePostDomain(1111, 2222, "title", "body")
    val compatibalePostData = ResponsePostData(1111, 2222, "title", "body")
    val compatibalePostDataList = listOf(ResponsePostData(1111, 2222, "title", "body"))


}

fun Faker.getSampleResponsePostData() = ResponsePostData(
    number().randomDigit(),
    number().randomDigit(),
    address().streetName(),
    address().fullAddress()
)

fun Faker.getSampleResponsePostDomain() = ResponsePostDomain(
    number().randomDigit(),
    number().randomDigit(),
    address().streetName(),
    address().fullAddress()
)

fun Faker.getListSampleResponsePostDomain(): List<ResponsePostDomain> {

    mutableListOf<ResponsePostDomain>().apply {
        for (i in 0..(0..10).random()) {
            add(getSampleResponsePostDomain())
        }
    }.also { return it }
}

fun getListResponsePostCompatible(): Pair<List<ResponsePostDomain> , List<ResponsePostData>> {

    val remote = mutableListOf<ResponsePostDomain>()
    val data = mutableListOf<ResponsePostData>()
    for (i in 0..(0..10).random()) {
        remote.add(CompatibleResponsePost.compatibalePostDomain)
        data.add(CompatibleResponsePost.compatibalePostData)
    }
    return Pair(remote , data)
}