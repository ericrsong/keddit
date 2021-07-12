package com.example.redditapp.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.RedditNewsItem
import com.example.redditapp.ViewType

/*
This encapsulates all the adapters used in the reddit app
 */
class NewsAdapters: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>

    // delegateAdapters contains different adapters. it contains newslist adapters
    // and loading adapters
    private var delegateAdapters =
        // SparseArrayCompat is a data structure where keys (Int) are mapped to objects
        // there can be gaps in the indices
        SparseArrayCompat<ViewTypeDelegateAdapter>()

    // loadingItem is really a subclass of NewsAdapters
    // this is also called "anonymous class" in java
    // here we are implementing the required interface
    private val loadingItem = object: ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(
            AdapterConstants.LOADING // key: Int
                , LoadingDelegateAdapter() // value: ViewTypeDelegateAdapter
        )
        delegateAdapters.put(
            AdapterConstants.NEWS // key
                , NewsDelegateAdapter() // value
        )
        items = ArrayList()
        items.add(loadingItem)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    // This calls each method for corresponding delegate adapters
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // get(0) get the first element in delegateAdapters, and then call its
        // method onCreateViewHolder. Then call get(1)
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    // This calls each method for corresponding delegate adapters
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))?.onBindViewHolder(holder,this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addNews(newsItems: List<RedditNewsItem>) {
        val initPosition = items.lastIndex
        items.addAll(initPosition, newsItems)
        this.notifyDataSetChanged()
    }

    fun clearAndAddNews(newsItems: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(newsItems)
        items.add(loadingItem)
        notifyItemRangeInserted(0, newsItems.size + 1)
    }

    fun getNews(): List<RedditNewsItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.NEWS}
                .map { it as RedditNewsItem}
    }

    private fun getLastPosition() = if (items.lastIndex == -1 ) 0 else items.lastIndex
}