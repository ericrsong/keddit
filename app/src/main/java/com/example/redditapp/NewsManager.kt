package com.example.redditapp

import com.example.redditapp.api.RestAPI
import io.reactivex.rxjava3.core.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(limit: String="10"): Observable<List<RedditNewsItem>> {
        return Observable.create{
                subscriber ->
            val callResponse = api.getNews("",limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {

                val news = response.body()?.data?.children?.map {
                    val item = it.data
                    RedditNewsItem(item.author,item.title,item.num_comments,
                        item.created, item.thumbnail, item.url)
                }
                subscriber.onNext(news)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}