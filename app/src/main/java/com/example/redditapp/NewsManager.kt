package com.example.redditapp

import com.example.redditapp.api.RestAPI
import io.reactivex.rxjava3.core.Observable

class NewsManager(private val api: RestAPI = RestAPI()) {

    fun getNews(after: String, limit: String="10"): Observable<RedditNews> {
        return Observable.create{
                subscriber ->
            val callResponse = api.getNews(after,limit)
            val response = callResponse.execute()

            if (response.isSuccessful) {

//                val news = response.body()?.data?.children?.map {
                val dataResponse = response.body()?.data
                val news = dataResponse?.children?.map{
                    val item = it.data
                    RedditNewsItem(item.author,item.title,item.num_comments,
                        item.created, item.thumbnail, item.url)
                }
                val redditNews = news?.let {
                    RedditNews(
                        dataResponse.after?:"",
                        dataResponse.before ?:"",
                        it
                    )
                }
                subscriber.onNext(redditNews)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}