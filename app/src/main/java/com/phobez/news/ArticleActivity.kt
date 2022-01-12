package com.phobez.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.phobez.news.article.ArticleListAdapter
import com.phobez.news.article.ArticleViewModel
import com.phobez.news.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityArticleBinding.inflate(layoutInflater)

        val title = intent.getStringExtra(getString(R.string.EXTRA_TOPIC_TITLE))
        val id = intent.getStringExtra(getString(R.string.EXTRA_TOPIC_ID))

        binding.topicTitle.text = title

        val articleList = binding.articleList

        val adapter = ArticleListAdapter(this)

        articleList.adapter = adapter

        val model: ArticleViewModel by viewModels()

        if (id != null) {
            model.loadArticles(id)
        }

        model.getArticles().observe(this, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })

        setContentView(binding.root)
    }
}