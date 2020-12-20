package com.java.task10.network

import com.java.task10.features.blog.blogPosts.model.PostData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiInterface {

    @GET("posts")
    fun getPostList(): Call<MutableList<PostData>>

    @GET("posts/{post_id}")
    fun getPostDetails(@Path("post_id") postId: Int): Call<PostData>



}