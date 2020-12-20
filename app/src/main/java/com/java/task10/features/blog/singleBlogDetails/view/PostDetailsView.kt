package com.java.task10.features.blog.singleBlogDetails.view

import com.java.task10.features.blog.blogPosts.model.PostData

interface PostDetailsView {

    fun handleProgressBarVisibility(isVisible: Boolean)
    fun onPostDetailsFetchSuccess(postList: PostData)
    fun onPostDetailsFetchFailure(errorMessage: String)
}