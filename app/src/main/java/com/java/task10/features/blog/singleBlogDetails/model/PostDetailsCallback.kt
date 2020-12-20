package com.java.task10.features.blog.singleBlogDetails.model

import com.java.task10.features.blog.blogPosts.model.PostData

interface PostDetailsCallback {
    fun onSuccess(postDetails: PostData)
    fun onFailure(throwable: Throwable)
}