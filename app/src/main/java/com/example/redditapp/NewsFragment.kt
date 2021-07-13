package com.example.redditapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditapp.adapter.NewsAdapters
import com.example.redditapp.databinding.NewsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsFragment:Fragment() {
    private var redditNews:RedditNews? = null
    private val newsManager by lazy { NewsManager() }

    private var subscriptions = CompositeDisposable()

    private val newsList: RecyclerView by lazy {
        binding.newList
    }

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: NewsFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsList.setHasFixedSize(true)

        val linearLayout = LinearLayoutManager(context)
        newsList.layoutManager = linearLayout
        newsList.clearOnScrollListeners()
        newsList.addOnScrollListener(InfiniteScrollListerner({requestNews()}, linearLayout))

        initAdapter()
        if (savedInstanceState == null ) {
//            val news = (1..10).map {
//                RedditNewsItem(
//                    "author $it",
//                    "Title $it",
//                    it,
//                    1457207701L - it * 200,
//                    "https://picsum.photos/200/300",
//                    "url"
//
//                )
//            }
//            (newsList.adapter as NewsAdapters).addNews(news)
            requestNews()
        }
    }

    override fun onResume() {
        super.onResume()
        var subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isDisposed) subscriptions.dispose()
        subscriptions.clear()
    }

    private fun requestNews() {
        /**
         * first time will send empty string for after parameter
         * Next time, we will have redditNews set with the next
         * page to navigate with after param.
         */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io()) // start another thread to separate tasks
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    retrievedNews ->
//                    (newsList.adapter as NewsAdapters).addNews(retrievedNews)
                    redditNews = retrievedNews
                    (newsList.adapter as NewsAdapters).addNews(retrievedNews.news)
                },
                {
                    e ->
                    Snackbar.make(newsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (newsList.adapter == null) {
            newsList.adapter = NewsAdapters()
        }
    }
}