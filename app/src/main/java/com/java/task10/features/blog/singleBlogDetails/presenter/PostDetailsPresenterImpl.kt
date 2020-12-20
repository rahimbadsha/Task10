package com.java.task10.features.blog.singleBlogDetails.presenter

import com.java.task10.features.blog.blogPosts.model.PostData
import com.java.task10.features.blog.singleBlogDetails.model.PostDetailsCallback
import com.java.task10.features.blog.singleBlogDetails.model.PostDetailsModel
import com.java.task10.features.blog.singleBlogDetails.model.PostDetailsModelImpl
import com.java.task10.features.blog.singleBlogDetails.view.PostDetailsView

class PostDetailsPresenterImpl(
    private val view: PostDetailsView
): PostDetailsPresenter {

    private val model: PostDetailsModel = PostDetailsModelImpl()

    override fun getPostDetails(postId: Int) {

        view.handleProgressBarVisibility(true)

        model.getBlogpostDetails(postId, object : PostDetailsCallback{
            override fun onSuccess(postDetails: PostData) {
                view.onPostDetailsFetchSuccess(postDetails)
                view.handleProgressBarVisibility(false)
            }

            override fun onFailure(throwable: Throwable) {
                view.handleProgressBarVisibility(false)
                if (throwable.localizedMessage != null)
                    view.onPostDetailsFetchFailure(throwable.localizedMessage)
                else
                    view.onPostDetailsFetchFailure("Something went wrong")
            }
        })
    }
}