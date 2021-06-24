package com.example.redditapp

import com.example.redditapp.adapter.AdapterConstants

data class RedditNewsItem(
    var author: String,
    var title: String,
    var numOfComments: Int,
    var created: Long,
    var thumbnail: String,
    var url: String): ViewType {
        override fun getViewType() = AdapterConstants.NEWS
    }
