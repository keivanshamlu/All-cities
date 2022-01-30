package com.shamlou.featureone.model.posts


data class ResponsePostsView(
    val posts : List<ResponsePostView>
)
data class ResponsePostView(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
)