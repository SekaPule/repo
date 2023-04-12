package com.example.repo.presentation.search.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repo.domain.interactor.GetNewsUseCase
import com.example.repo.presentation.base.mapper.NewsViewMapper
import com.example.repo.presentation.base.model.NewsView
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
        emitAll(getNewsUseCase.execute().map { list ->
            list.map { domainModel ->
                newsViewMapper.mapFromDomainModel(type = domainModel)
            }
        })
    }
}