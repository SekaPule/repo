package com.example.repo.presentation.newslist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repo.domain.interactor.GetNewsUseCase
import com.example.repo.domain.interactor.InitDataForCurrentSessionUseCase
import com.example.repo.presentation.base.mapper.NewsViewMapper
import com.example.repo.presentation.base.model.NewsView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val initDataForCurrentSessionUseCase: InitDataForCurrentSessionUseCase,
    private val newsViewMapper: NewsViewMapper
) : ViewModel() {

    private val _notCheckedNewsCounter = MutableStateFlow(0)
    val notCheckedNewsCounter = _notCheckedNewsCounter.asStateFlow()

    private var _news = MutableStateFlow(emptyList<NewsView>())
    val news = _news.asStateFlow()
    var allNews = emptyList<NewsView>()

    fun initNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.execute().collect { newsList ->
                allNews = newsList.map { domainModel ->
                    newsViewMapper.mapFromDomainModel(type = domainModel)
                }
                _news.emit(allNews)
            }
        }

        setNotCheckedNewsCounter()
    }

    fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            initDataForCurrentSessionUseCase.execute()
        }
    }

    fun setNotCheckedNewsCounter() {
        viewModelScope.launch {
            try {
                _news.collect { newsList ->
                    _notCheckedNewsCounter.emit(newsList.count { !it.isChecked })
                }
            } catch (e: Throwable) {
                e.localizedMessage?.let { Log.e("TAG", it) }
            }
        }
    }

    fun filterNews(filters: List<String>) {
        viewModelScope.launch {
            if (filters.isNotEmpty()) {
                _news.collect { newsList ->
                    _news.emit(newsList.filter { news ->
                        filters.any { filter ->
                            news.category!!.contains(filter)
                        }
                    })
                }
            } else {
                _news.emit(allNews)
            }
        }
        setNotCheckedNewsCounter()
    }
}