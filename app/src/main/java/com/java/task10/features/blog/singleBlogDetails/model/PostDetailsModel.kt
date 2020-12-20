package com.java.task10.features.blog.singleBlogDetails.model


interface PostDetailsModel {

    fun getBlogpostDetails(postId: Int, postDetailsCallback: PostDetailsCallback)
}