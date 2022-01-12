package com.phobez.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.phobez.news.databinding.ActivityMainBinding
import com.phobez.news.topic.TopicListAdapter
import com.phobez.news.topic.TopicViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val topicList = binding.topicList

        val adapter = TopicListAdapter(this)

        topicList.adapter = adapter

        val model: TopicViewModel by viewModels()

        model.getTopics().observe(this, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })

        setContentView(binding.root)
    }
}