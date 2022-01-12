package com.phobez.news.topic

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.phobez.news.ArticleActivity
import com.phobez.news.R
import com.phobez.news.databinding.TopicListItemBinding

class TopicListAdapter(private val context: Context) :
    RecyclerView.Adapter<TopicListAdapter.TopicViewHolder>() {

    private var data: List<Topic> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding =
            TopicListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = data[position]

        holder.topicTitle.text = item.title
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArticleActivity::class.java)

            intent.putExtra(context.getString(R.string.EXTRA_TOPIC_ID), item.id)
            intent.putExtra(context.getString(R.string.EXTRA_TOPIC_TITLE), item.title)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<Topic>) {
        if (this.data.isEmpty()) {
            this.data = data
        }
    }

    class TopicViewHolder(binding: TopicListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val topicTitle = binding.topicTitle
    }
}