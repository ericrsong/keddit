package com.example.redditapp.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.R
import com.example.redditapp.RedditNewsItem
import com.example.redditapp.ViewType
import com.example.redditapp.commons.getFriendlyTime
import com.example.redditapp.commons.inflate
import com.example.redditapp.commons.loadImage

class NewsDelegateAdapter: ViewTypeDelegateAdapter {

//    class ItemViewHolder(private val view: View) {
//        val img_thumbnail: ImageView = view.findViewById(R.id.img_thumbnail)
//        val description : TextView = view.findViewById(R.id.description)
//        val author: TextView = view.findViewById(R.id.author)
//        val comments: TextView = view.findViewById(R.id.comments)
//        val time: TextView = view.findViewById(R.id.time)
//    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
//        val adapterLayout = LayoutInflater.from(parent.context)
//            .inflate(R.layout.news_item,parent,false)
//        return ItemViewHolder(adapterLayout)
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        val tmpHolder = holder as TurnsViewHolder
//        // here we update each View
//        // Question: how will item be passed in?
        tmpHolder.bind(holder,item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup):
        RecyclerView.ViewHolder(
            // we used the extension function that we wrote in commons/Extensions.kt
            parent.inflate(R.layout.news_item_loading)) {

        fun bind(holder: RecyclerView.ViewHolder, item: RedditNewsItem) = with(item){
            val ITEMVIEW = holder.itemView
            val imageView: ImageView = ITEMVIEW.findViewById(R.id.img_thumbnail)
            imageView.loadImage(thumbnail)
            val textView1: TextView = holder.itemView.findViewById(R.id.description)
            textView1.text = title
            val textView2: TextView = holder.itemView.findViewById(R.id.author)
            textView2.text = author
            val textView3: TextView = holder.itemView.findViewById(R.id.comments)
            textView3.text = "${numOfComments} comments"
            val textView4: TextView = holder.itemView.findViewById(R.id.time)
            textView4.text = created.getFriendlyTime()
        }
    }
}