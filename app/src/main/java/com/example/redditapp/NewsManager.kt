package com.example.redditapp

import io.reactivex.rxjava3.core.Observable

class NewsManager {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create{
            subscriber ->

            val news = (1..10).map {
                RedditNewsItem(
                    "author $it",
                    "Title $it",
                    it,
                    1457207701L - it * 200,
                    "https://picsum.photos/200/300",
                    "url"

                )
            }
            subscriber.onNext(news)
        }
    }
}