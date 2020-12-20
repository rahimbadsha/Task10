package com.java.task10.features.blog.blogPosts.view

import com.java.task10.features.blog.blogPosts.model.PostData

interface PostListView {
    fun handleProgressBarVisibility(isVisible: Boolean)
    fun onPostListFetchSuccess(postList: MutableList<PostData>)
    fun onPostListFetchFailure(errorMessage: String)
}