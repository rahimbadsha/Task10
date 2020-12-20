package com.java.task10.features.blog.blogPosts.model

import java.io.Serializable

data class PostData (
    val id: Int,
    val date: String,
    val modifiedDate: String,
    val title: Title,
    val content: Description,
    val jetpack_featured_media_url: String
): Serializable {
    data class Title(val rendered: String)
    data class Description(val rendered: String)
}