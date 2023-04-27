package com.example.repo.presentation.details.vm

import com.example.repo.presentation.details.views.BottomIcons
import com.example.repo.presentation.details.views.component.DonationButtons
import com.example.search_feature.presentation.model.NewsView

data class DetailsViewState(
//    val detailsData: NewsView,
    val selectedBottomNavigationItem: BottomIcons = BottomIcons.CLOTH,
    val isShowDialog: Boolean = false,
    val selectedDonationsButton: DonationButtons = DonationButtons.MIN
)
