package com.example.redditapp

import com.example.redditapp.adapter.AdapterConstants
import com.squareup.moshi.Json

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>
)

data class RedditNewsItem(
    var author: String,
    var title: String,
    var numOfComments: Int,
    @Json(name= "created_utc" ) var created: Long,
    var thumbnail: String,
    var url: String): ViewType {
        override fun getViewType() = AdapterConstants.NEWS
    }
