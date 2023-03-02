package com.example.repo.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repo.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _news = MutableStateFlow(mutableListOf<News>())
    val news: StateFlow<MutableList<News>>
        get() = _news

    fun setNews(news: MutableList<News>?) {
        news?.let {
            viewModelScope.launch {
                try {
                    _news.emit(it)
                }catch (e: Error){
                    e.localizedMessage?.let { Log.e("TAG", it) }
                }
            }
        }
    }
}