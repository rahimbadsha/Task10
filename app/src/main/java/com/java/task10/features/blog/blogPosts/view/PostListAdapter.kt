package com.java.task10.features.blog.blogPosts.view

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task10.R
import com.java.task10.features.blog.blogPosts.model.PostData
import java.text.SimpleDateFormat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PostListAdapter(
    private val postList: MutableList<PostData>,
    private val itemClickPostListListener: OnItemClickPostList
): RecyclerView.Adapter<PostListViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.blog_list, parent,false)
        return PostListViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  postList.size
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {

        val post = postList[position]

        Glide.with(holder.iv_postImage)
            .load(post.jetpack_featured_media_url)
            .centerCrop()
            .into(holder.iv_postImage)

        @SuppressWarnings("deprecation")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            holder.tv_title.text = Html.fromHtml(post.title.rendered, Html.FROM_HTML_MODE_COMPACT)
        }
        else
        {
            holder.tv_title.text = Html.fromHtml(post.title.rendered, Html.FROM_HTML_MODE_COMPACT)
        }

        holder.tv_author.text = holder.itemView.context.getString(R.string.post_author_name)
        //holder.tv_date.text = post.modifiedDate


        //holder.tv_date.text = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(post.date).toString()

        val inputFormatter =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val outputFormatter =  SimpleDateFormat("dd-MMM-yyyy HH:mm")
        val date = inputFormatter.parse(post.date)
        holder.tv_date.text= outputFormatter.format(date)

        holder.itemView.setOnClickListener {
            itemClickPostListListener.onPostItemClickListener(position)
        }
    }
}