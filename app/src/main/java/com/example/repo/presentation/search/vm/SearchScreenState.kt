package com.example.repo.presentation.search.vm

sealed class SearchScreenState{
    object EmptySearchText: SearchScreenState()
    object NotEmptySearchText: SearchScreenState()
}
