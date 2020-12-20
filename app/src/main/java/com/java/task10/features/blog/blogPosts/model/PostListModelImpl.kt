package com.java.task10.features.blog.blogPosts.model

import com.java.task10.network.PostApiInterface
import com.java.task10.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListModelImpl: PostListModel {

    private val apiInterface = RetrofitClient.getClient().create(PostApiInterface::class.java)
    private val call = apiInterface.getPostList()

    override fun getBlogPostList(postListCallback: PostListCallback) {

        call.enqueue(object : Callback<MutableList<PostData>>{
            override fun onFailure(call: Call<MutableList<PostData>>, t: Throwable) {
                postListCallback.onFailure(t)
            }

            override fun onResponse(call: Call<MutableList<PostData>>, response: Response<MutableList<PostData>>) {
                response.body()?.let {
                    postListCallback.onSuccess(it)
                }
            }

        })
    }


}