package com.java.task10.features.blog.blogPosts.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task10.R
import com.java.task10.core.BaseActivity
import com.java.task10.features.blog.blogPosts.model.PostData
import com.java.task10.features.blog.blogPosts.presenter.PostListPresenter
import com.java.task10.features.blog.blogPosts.presenter.PostListPresenterImpl
import com.java.task10.features.blog.singleBlogDetails.view.SinglePostDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(), PostListView {

    //Variable declaration here
    private lateinit var presenter: PostListPresenter


    override fun setLayoutId(): Int = R.layout.activity_main

    override fun setToolbar(): Toolbar {
        toolbar.title = getString(R.string.app_name)
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = false


    override fun handleProgressBarVisibility(isVisible: Boolean) {
        if (isVisible)
            progress.visibility= View.VISIBLE
        else
            progress.visibility= View.GONE
    }

    override fun onPostListFetchSuccess(postList: MutableList<PostData>) {
        initPostListAdapter(postList)
    }

    override fun onPostListFetchFailure(errorMessage: String) {
        //showToast(errorMessage)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    // Oncreate Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PostListPresenterImpl(this)

        presenter.getPostList()

    }


// Activity Methods

    @SuppressLint("WrongConstant")
    private fun initPostListAdapter(postList: MutableList<PostData>) {

        val postAdapter = PostListAdapter(postList, object: OnItemClickPostList{
            override fun onPostItemClickListener(position: Int) {
                //showToast(postList[position].title.rendered)

                val intent = Intent(this@MainActivity, SinglePostDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("postId", postList[position].id)

                intent.putExtras(bundle)
                startActivity(intent)

                Toast.makeText(this@MainActivity, postList[position].title.rendered, Toast.LENGTH_LONG).show()
            }

        })

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = postAdapter
    }

}
