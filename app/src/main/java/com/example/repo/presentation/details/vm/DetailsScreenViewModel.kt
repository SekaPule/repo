package com.example.repo.presentation.details.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repo.presentation.newslist.viewmodel.SingleLiveEvent
import com.example.search_feature.presentation.model.NewsView
import javax.inject.Inject

class DetailsScreenViewModel @Inject constructor() : ViewModel() {

    private val _closeDetails = SingleLiveEvent<Boolean>()
    val closeDetails: LiveData<Boolean> = _closeDetails

    private val _details = MutableLiveData<NewsView>()
    val details: LiveData<NewsView> = _details

    fun obtainIntent(detailsIntent: DetailsIntent) {
        when(detailsIntent){
            DetailsIntent.CloseDetailsIntent -> _closeDetails.value = true
            is DetailsIntent.InitDetailsDataIntent -> _details.value = detailsIntent.newsView
        }
    }
}