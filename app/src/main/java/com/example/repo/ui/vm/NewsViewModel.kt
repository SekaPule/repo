package com.example.repo.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repo.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _notCheckedNewsCounter = MutableStateFlow(0)
    val notCheckedNewsCounter = _notCheckedNewsCounter.asStateFlow()

    private var _news = listOf<News>()
    val news = _news

    fun setNotCheckedNewsCounter(counter: Int) {
        viewModelScope.launch {
            try {
                _notCheckedNewsCounter.emit(counter)
            } catch (e: Throwable) {
                e.localizedMessage?.let { Log.e("TAG", it) }
            }
        }
    }

    fun setNews(news: List<News>){
        _news = news
    }
}