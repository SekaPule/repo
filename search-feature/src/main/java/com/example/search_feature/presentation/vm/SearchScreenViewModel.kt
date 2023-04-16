package com.example.search_feature.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.News
import com.example.search_feature.interactor.GetNewsUseCase
import com.example.search_feature.presentation.mapper.NewsViewMapper
import com.example.search_feature.presentation.model.NewsView
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchScreenViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val newsViewMapper: NewsViewMapper
) : ViewModel() {

    private val _searchText = MutableLiveData<String>()

    val searchText: LiveData<String>
        get() = _searchText

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun getNews(): Flow<List<NewsView>> = flow {
        emitAll(getNewsUseCase.execute().map { list: List<News> ->
            list.map { domainModel: News ->
                newsViewMapper.mapFromDomainModel(type = domainModel)
            }
        })
    }
}