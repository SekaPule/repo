package com.example.repo.ui.vm

import androidx.lifecycle.ViewModel
import com.example.repo.model.News
import io.reactivex.rxjava3.subjects.PublishSubject

class NewsViewModel : ViewModel() {
    private val _news = PublishSubject.create<MutableList<News>?>()
    val news: PublishSubject<MutableList<News>>?
        get() = _news

    fun setNews(news: MutableList<News>?) {
        news?.let {
            _news.onNext(it)
        }
    }
}