package com.example.repo.presentation.filters.vm

import androidx.lifecycle.ViewModel
import com.example.repo.presentation.base.model.FilterView

class FilterScreenViewModel : ViewModel() {
    private var _savedFilters: List<FilterView>? = null
    val savedFilters: List<FilterView>?
        get() = _savedFilters

    fun saveFilters(filters: List<FilterView>?) {
        _savedFilters = filters
    }

}
