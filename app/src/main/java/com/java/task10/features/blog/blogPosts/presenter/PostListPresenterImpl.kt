package com.java.task10.features.blog.blogPosts.presenter

import com.java.task10.features.blog.blogPosts.model.PostData
import com.java.task10.features.blog.blogPosts.model.PostListCallback
import com.java.task10.features.blog.blogPosts.model.PostListModel
import com.java.task10.features.blog.blogPosts.model.PostListModelImpl
import com.java.task10.features.blog.blogPosts.view.PostListView

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PostListPresenterImpl(
    private val view: PostListView
): PostListPresenter {

    private val model: PostListModel = PostListModelImpl()

    override fun getPostList() {

        view.handleProgressBarVisibility(true)

        model.getBlogPostList(object : PostListCallback{
            override fun onSuccess(postList: MutableList<PostData>) {
                view.handleProgressBarVisibility(false)
                view.onPostListFetchSuccess(postList)
            }

            override fun onFailure(throwable: Throwable) {
                view.handleProgressBarVisibility(false)
                if (throwable.localizedMessage != null)
                {
                    view.onPostListFetchFailure(throwable.localizedMessage)
                }
                else
                    view.onPostListFetchFailure("Something Went Wrong")
            }
        })
    }
}