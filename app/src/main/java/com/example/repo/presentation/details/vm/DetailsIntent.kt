package com.example.repo.presentation.details.vm

import com.example.repo.presentation.details.views.BottomIcons
import com.example.repo.presentation.details.views.component.DonationButtons
import com.example.search_feature.presentation.model.NewsView

sealed class DetailsIntent {

    object CloseDetailsIntent: DetailsIntent()
    data class InitDetailsDataIntent(val newsView: NewsView): DetailsIntent()
    data class SelectBottomItemIntent(val bottomIcons: BottomIcons): DetailsIntent()
    data class SelectDonationButtonIntent(val donationButtons: DonationButtons): DetailsIntent()
    object ShowDialogIntent: DetailsIntent()
    object HideDialogIntent: DetailsIntent()
    data class MoneyTextFieldValueChangeIntent(val value: String): DetailsIntent()
    object ConfirmDonationIntent: DetailsIntent()
}
