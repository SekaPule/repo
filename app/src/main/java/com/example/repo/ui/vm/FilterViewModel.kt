package com.example.repo.ui.vm

import androidx.lifecycle.ViewModel
import com.example.repo.model.FilterItem

class FilterViewModel : ViewModel() {
    private var _savedFilters: List<FilterItem>? = null
    val savedFilters: List<FilterItem>?
        get() = _savedFilters

    fun saveFilters(filters: List<FilterItem>?) {
        _savedFilters = filters
    }

}
