package com.java.task10.features.blog.blogPosts.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.blog_list.view.*

class PostListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tv_date: TextView = itemView.tv_post_date_value
    val tv_title: TextView = itemView.tv_post_title
    val tv_author: TextView = itemView.tv_post_author_name
    val iv_postImage: ImageView = itemView.iv_post_image
}