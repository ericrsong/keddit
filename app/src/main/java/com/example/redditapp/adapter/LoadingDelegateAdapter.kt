package com.example.redditapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.R
import com.example.redditapp.ViewType
import com.example.redditapp.commons.inflate

class LoadingDelegateAdapter: ViewTypeDelegateAdapter {

    class TurnsViewHolder(parent: ViewGroup):
        RecyclerView.ViewHolder(
            // we used the extension function that we wrote in commons/Extensions.kt
            parent.inflate(R.layout.news_item_loading))

    override fun onCreateViewHolder(parent: ViewGroup) =
        TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
        item: ViewType
    ) {}

}