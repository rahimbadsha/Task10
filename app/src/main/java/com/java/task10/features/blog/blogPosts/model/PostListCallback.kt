package com.java.task10.features.blog.blogPosts.model

interface PostListCallback {

    fun onSuccess(postList: MutableList<PostData>)
    fun onFailure(throwable: Throwable)
}