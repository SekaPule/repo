package com.example.repo.presentation.newslist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repo.domain.interactor.GetNewsUseCaseImpl
import com.example.repo.domain.interactor.InitDataForCurrentSessionUseCase
import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import com.example.search_feature.presentation.model.NewsView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCaseImpl,
    private val initDataForCurrentSessionUseCase: InitDataForCurrentSessionUseCase,
    private val newsViewMapper: NewsViewMapperImpl
) : ViewModel() {

    fun getNews(): Flow<List<NewsView>> = flow {
        emitAll(getNewsUseCase.execute().map { list ->
            list.map { domainModel ->
                newsViewMapper.mapFromDomainModel(type = domainModel)
            }
        })
    }

    fun initData() {
        viewModelScope.launch(Dispatchers.IO) {
            initDataForCurrentSessionUseCase.execute()
        }
    }

    private val _notCheckedNewsCounter = MutableStateFlow(0)
    val notCheckedNewsCounter = _notCheckedNewsCounter.asStateFlow()

    private var _news = listOf<NewsView>()
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

    fun setNews(news: List<NewsView>) {
        _news = news
    }
}