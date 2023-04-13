package com.example.search_feature.presentation.vm

sealed class SearchScreenState{
    object EmptySearchText: SearchScreenState()
    object NotEmptySearchText: SearchScreenState()
}
