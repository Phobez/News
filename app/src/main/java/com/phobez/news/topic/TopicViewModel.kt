package com.phobez.news.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phobez.news.QueryUtils
import kotlinx.coroutines.launch
import org.json.JSONObject

class TopicViewModel : ViewModel() {

    private val topics: MutableLiveData<List<Topic>> by lazy {
        MutableLiveData<List<Topic>>()
    }

    init {
        loadTopics()
    }

    fun getTopics(): LiveData<List<Topic>> {
        return topics
    }

    private fun loadTopics() {
        viewModelScope.launch {
            val jsonResp = QueryUtils.makeHttpRequest(TOPIC_REQUEST_URL)

            if (jsonResp.isNotBlank()) {
                val res = JSONObject(jsonResp).getJSONObject(RESPONSE_KEY).getJSONArray(RESULTS_KEY)

                val maxIndex = res.length() - 1

                val newTopics = ArrayList<Topic>()

                for (i in 1..maxIndex) {
                    val obj = res.getJSONObject(i)
                    val id = obj.getString(ID_KEY)
                    val title = obj.getString(TITLE_KEY)

                    newTopics.add(Topic(id, title))
                }

                topics.postValue(newTopics)
            }
        }
    }

    companion object {
        private const val TOPIC_REQUEST_URL =
            "https://content.guardianapis.com/sections?api-key=test"
        private const val RESPONSE_KEY = "response"
        private const val RESULTS_KEY = "results"
        private const val ID_KEY = "id"
        private const val TITLE_KEY = "webTitle"
    }
}