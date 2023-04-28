package com.example.repo.presentation.details.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.News
import com.example.repo.presentation.newslist.viewmodel.SingleLiveEvent
import com.example.search_feature.presentation.model.NewsView
import javax.inject.Inject

class DetailsScreenViewModel @Inject constructor() : ViewModel() {

    private val _closeDetails = SingleLiveEvent<Boolean>()
    val closeDetails: LiveData<Boolean> = _closeDetails

    private val _viewState = MutableLiveData<DetailsViewState>(DetailsViewState())
    val viewState: LiveData<DetailsViewState> = _viewState

    private val _detailsData = MutableLiveData<NewsView>()
    val detailsData: LiveData<NewsView> = _detailsData


    fun obtainIntent(detailsIntent: DetailsIntent) {
        when (detailsIntent) {
            DetailsIntent.CloseDetailsIntent -> _closeDetails.value = true
            is DetailsIntent.InitDetailsDataIntent -> _detailsData.value = detailsIntent.newsView
            is DetailsIntent.SelectBottomItemIntent -> _viewState.value =
                _viewState.value!!.copy(selectedBottomNavigationItem = detailsIntent.bottomIcons)
            DetailsIntent.HideDialogIntent -> _viewState.postValue(
                _viewState.value!!.copy(isShowDialog = false)

            )
            DetailsIntent.ShowDialogIntent -> _viewState.postValue(
                _viewState.value!!.copy(isShowDialog = true)
            )
            is DetailsIntent.SelectDonationButtonIntent -> _viewState.value =
                _viewState.value!!.copy(selectedDonationsButton = detailsIntent.donationButtons)

        }
    }
}