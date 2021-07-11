package com.example.redditapp.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

class RestAPI() {
    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.reddit.com")
            .build()

        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after:String, limit:String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}