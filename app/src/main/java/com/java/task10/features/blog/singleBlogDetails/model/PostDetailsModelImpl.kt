package com.java.task10.features.blog.singleBlogDetails.model

import com.java.task10.features.blog.blogPosts.model.PostData
import com.java.task10.network.PostApiInterface
import com.java.task10.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsModelImpl: PostDetailsModel {

    private val apiInterface = RetrofitClient.getClient().create(PostApiInterface::class.java)
    private val calls = apiInterface.getPostList()

    override fun getBlogpostDetails(postId: Int, postDetailsCallback: PostDetailsCallback) {
        val call = apiInterface.getPostDetails(postId)

        call.enqueue(object : Callback<PostData>{
            override fun onFailure(call: Call<PostData>, t: Throwable) {
                postDetailsCallback.onFailure(t)
            }

            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                response.body()?.let {
                    postDetailsCallback.onSuccess(it)
                }
            }

        })
    }
}