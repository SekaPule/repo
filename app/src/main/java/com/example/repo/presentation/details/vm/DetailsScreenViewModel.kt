package com.example.repo.presentation.details.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.example.repo.data.DonationWorker
import com.example.repo.data.WorkManagerConfig.DETAILS_ID
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_JSON
import com.example.repo.data.WorkManagerConfig.DETAILS_TITLE
import com.example.repo.data.WorkManagerConfig.DONATION_VALUE
import com.example.repo.data.WorkManagerConfig.WORK_NAME
import com.example.repo.presentation.newslist.viewmodel.SingleLiveEvent
import com.example.search_feature.presentation.model.NewsView
import com.google.gson.Gson
import javax.inject.Inject

class DetailsScreenViewModel @Inject constructor(context: Context) : ViewModel() {

    private val _closeDetails = SingleLiveEvent<Boolean>()
    val closeDetails: LiveData<Boolean> = _closeDetails

    private val _viewState = MutableLiveData<DetailsViewState>(DetailsViewState())
    val viewState: LiveData<DetailsViewState> = _viewState

    private val _detailsData = MutableLiveData<NewsView>()
    val detailsData: LiveData<NewsView> = _detailsData

    private val workManager = WorkManager.getInstance(context.applicationContext)


    fun obtainIntent(detailsIntent: DetailsIntent) {
        when (detailsIntent) {
            DetailsIntent.CloseDetailsIntent -> _closeDetails.value = true
            DetailsIntent.HideDialogIntent -> _viewState.postValue(
                _viewState.value!!.copy(isShowDialog = false)
            )
            DetailsIntent.ShowDialogIntent -> _viewState.postValue(
                _viewState.value!!.copy(isShowDialog = true)
            )
            is DetailsIntent.InitDetailsDataIntent -> _detailsData.value = detailsIntent.newsView
            is DetailsIntent.SelectBottomItemIntent -> _viewState.postValue(
                _viewState.value!!.copy(selectedBottomNavigationItem = detailsIntent.bottomIcons)
            )
            is DetailsIntent.SelectDonationButtonIntent -> _viewState.postValue(
                _viewState.value!!.copy(
                    selectedDonationsButton = detailsIntent.donationButtons,
                    moneyTextFieldValue = detailsIntent.donationButtons.money.toString(),
                    isTransactButtonEnabled = validateMoneyTextField(detailsIntent.donationButtons.money.toString())
                )
            )
            is DetailsIntent.MoneyTextFieldValueChangeIntent -> _viewState.postValue(
                _viewState.value!!.copy(
                    moneyTextFieldValue = detailsIntent.value,
                    isTransactButtonEnabled = validateMoneyTextField(detailsIntent.value)
                )
            )
            DetailsIntent.ConfirmDonationIntent -> {
                startWorkerRequest(
                    newsView = detailsData.value!!,
                    donationValue = _viewState.value?.moneyTextFieldValue?.toInt() ?: 0,
                )
                _viewState.postValue(_viewState.value!!.copy(isShowDialog = false))
            }
        }
    }

    private fun validateMoneyTextField(value: String): Boolean =
        if (value == "") {
            false
        } else {
            value.toInt() in MONEY_LOWEST_BOUND..MONEY_HIGHEST_BOUND
        }

    private fun startWorkerRequest(newsView: NewsView, donationValue: Int) {
        Log.e(this.javaClass.toString(), "AAAAAAAAAAAAAAAAAAA")
        val donate = OneTimeWorkRequestBuilder<DonationWorker>()
            .setInputData(
                workDataOf(
                    DETAILS_ID to newsView.id,
                    DETAILS_TITLE to newsView.title,
                    DONATION_VALUE to donationValue.toString(),
                    DETAILS_ITEM_JSON to Gson().toJson(newsView)
                )
            )

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        donate.setConstraints(constraints)

        workManager.beginUniqueWork(
            WORK_NAME,
            ExistingWorkPolicy.APPEND,
            donate.build()
        ).enqueue()
    }

    companion object {
        private const val MONEY_LOWEST_BOUND = 1
        private const val MONEY_HIGHEST_BOUND = 9_999_999
    }
}