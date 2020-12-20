package com.java.task10.features.blog.singleBlogDetails.view

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.task10.R
import com.java.task10.core.BaseActivity
import com.java.task10.features.blog.blogPosts.model.PostData
import com.java.task10.features.blog.singleBlogDetails.presenter.PostDetailsPresenter
import com.java.task10.features.blog.singleBlogDetails.presenter.PostDetailsPresenterImpl
import kotlinx.android.synthetic.main.activity_single_post_details.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat

class SinglePostDetailsActivity : BaseActivity(), PostDetailsView {

    private lateinit var postDetailsPresenter: PostDetailsPresenter

    override fun setLayoutId(): Int = R.layout.activity_single_post_details

    override fun setToolbar(): Toolbar {
        toolbar.title = getString(R.string.blog_details)
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = true


    override fun handleProgressBarVisibility(isVisible: Boolean) {
        if (isVisible)
            post_details_progress.visibility = View.VISIBLE
        else
            post_details_progress.visibility = View.GONE
    }

    override fun onPostDetailsFetchSuccess(postDetails: PostData) {
        blogPostDetailsView(postDetails)
    }

    override fun onPostDetailsFetchFailure(errorMessage: String) {
        showToast(errorMessage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val postId = bundle!!.getInt("postId", 0)
        postDetailsPresenter = PostDetailsPresenterImpl(this)
        postDetailsPresenter.getPostDetails(postId)
    }

    fun blogPostDetailsView(postDetails: PostData)
    {
        Glide.with(iv_single_post_image)
            .load(postDetails.jetpack_featured_media_url)
            .centerCrop()
            .into(iv_single_post_image)

        tv_single_post_title.text = Html.fromHtml(postDetails.title.rendered)
        tv_single_post_content.text = Html.fromHtml(postDetails.content.rendered)
        tv_single_post_author_name.text = getString(R.string
            .post_author_name)

        //date
        val inputFormatter = SimpleDateFormat("dd-MM-yyyy")
        val outputFormatter = SimpleDateFormat("dd-MM-yyyy")
        val date = inputFormatter.parse(postDetails.date)
        tv_single_post_date_value.text = outputFormatter.format(date)

    }
}
