package com.example.repo.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private val _searchText = MutableLiveData<String>()

    val searchText: LiveData<String>
        get() = _searchText

    fun setSearchText(text: String) {
        _searchText.value = text
    }
}