package com.example.repo.presentation.details.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repo.R
import com.example.repo.presentation.details.views.component.DonationButtons
import com.example.repo.presentation.details.views.component.HelpMoneyDialog
import com.example.repo.presentation.details.vm.DetailsIntent
import com.example.repo.presentation.details.vm.DetailsScreenViewModel
import com.example.repo.presentation.details.vm.DetailsViewState
import com.example.repo.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel
) {
    val viewState = viewModel.viewState.observeAsState(DetailsViewState())
    val detailsData = viewModel.detailsData.observeAsState()

    with(viewState.value) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = detailsData.value?.title ?: " ",
                            style = AppTheme.typography.textStyle2,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        titleContentColor = AppTheme.colors.white,
                        containerColor = AppTheme.colors.lightGreen,
                        actionIconContentColor = AppTheme.colors.white,
                        navigationIconContentColor = AppTheme.colors.white,
                    ),
                    actions = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null,
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_xs))
                        )
                    },
                    navigationIcon = {
                        Icon(
                            painter = painterResource(id = com.example.auth_feature.R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = com.example.auth_feature.R.string.arrow_back_description),
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = com.example.auth_feature.R.dimen.spacing_xs))
                                .clickable { viewModel.obtainIntent(DetailsIntent.CloseDetailsIntent) }
                        )
                    }
                )
            },
            containerColor = AppTheme.colors.white,
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    containerColor = AppTheme.colors.offWhite,
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(1f),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = {
                                    viewModel.obtainIntent(
                                        DetailsIntent.SelectBottomItemIntent(
                                            BottomIcons.CLOTH
                                        )
                                    )
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_shirt),
                                        contentDescription = null,
                                        tint = if (selectedBottomNavigationItem == BottomIcons.CLOTH) AppTheme.colors.lightGreen else AppTheme.colors.grey
                                    )
                                }

                                Text(
                                    text = stringResource(id = R.string.help_cloth),
                                    fontSize = 10.sp,
                                    color = AppTheme.colors.black38,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = {
                                    viewModel.obtainIntent(
                                        DetailsIntent.SelectBottomItemIntent(
                                            BottomIcons.VOLUNTEER
                                        )
                                    )
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_hands),
                                        contentDescription = null,
                                        tint = if (selectedBottomNavigationItem == BottomIcons.VOLUNTEER) AppTheme.colors.lightGreen else AppTheme.colors.grey
                                    )
                                }

                                Text(
                                    text = stringResource(id = R.string.become_vol),
                                    fontSize = 10.sp,
                                    color = AppTheme.colors.black38,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = {
                                    viewModel.obtainIntent(
                                        DetailsIntent.SelectBottomItemIntent(
                                            BottomIcons.PROF
                                        )
                                    )
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_tools),
                                        contentDescription = null,
                                        tint = if (selectedBottomNavigationItem == BottomIcons.PROF) AppTheme.colors.lightGreen else AppTheme.colors.grey
                                    )
                                }

                                Text(
                                    text = stringResource(id = R.string.prof_hellp),
                                    fontSize = 10.sp,
                                    color = AppTheme.colors.black38,
                                    textAlign = TextAlign.Center
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(
                                    modifier = Modifier.testTag("HELP_WITH_MONEY_BTN_TAG"),
                                    onClick = {
                                    viewModel.run {
                                        obtainIntent(
                                            DetailsIntent.SelectBottomItemIntent(BottomIcons.MONEY)
                                        )
                                        obtainIntent(DetailsIntent.ShowDialogIntent)
                                    }
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_coins),
                                        contentDescription = null,
                                        tint = if (selectedBottomNavigationItem == BottomIcons.MONEY) AppTheme.colors.lightGreen else AppTheme.colors.grey
                                    )
                                }

                                Text(
                                    text = stringResource(id = R.string.help_money),
                                    fontSize = 10.sp,
                                    color = AppTheme.colors.black38,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            HelpMoneyDialog(
                moneyValue = moneyTextFieldValue,
                isTransactButtonEnabled = isTransactButtonEnabled,
                moneyTextFieldValueChange = { value: String ->
                    viewModel.obtainIntent(
                        DetailsIntent.MoneyTextFieldValueChangeIntent(
                            value
                        )
                    )
                },
                isShowDialog = isShowDialog,
                setShowDialog = { viewModel.obtainIntent(DetailsIntent.HideDialogIntent) },
                selectedDonationButton = selectedDonationsButton,
                setSelectedButton = { donationButtons: DonationButtons ->
                    viewModel.obtainIntent(
                        DetailsIntent.SelectDonationButtonIntent(donationButtons)
                    )
                },
                confirmDonation = { viewModel.obtainIntent(DetailsIntent.ConfirmDonationIntent) }
            )

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = contentPadding)
                    .fillMaxWidth()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.spacing_m),
                                top = dimensionResource(id = R.dimen.spacing_l),
                                end = dimensionResource(id = R.dimen.spacing_m)
                            )
                            .fillMaxWidth()
                    ) {

                        Text(
                            text = detailsData.value?.title ?: "",
                            style = AppTheme.typography.textStyle6,
                            color = AppTheme.colors.blueGrey,
                            textAlign = TextAlign.Start
                        )

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(
                                        end = dimensionResource(
                                            id = R.dimen.small_content_spacing
                                        )
                                    )
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.date_top_spacing)
                                    ),
                                colorFilter = ColorFilter.tint(AppTheme.colors.grey)
                            )

                            Text(
                                text = detailsData.value?.daysLeftText ?: "",
                                fontSize = 12.sp,
                                color = AppTheme.colors.grey
                            )
                        }

                        Text(
                            text = detailsData.value?.organization ?: "",
                            fontSize = 12.sp,
                            color = AppTheme.colors.black60,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_content_spacing))
                        )

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_content_spacing))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(
                                        end = dimensionResource(
                                            id = R.dimen.spacing_m
                                        )
                                    )
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.date_top_spacing)
                                    ),
                            )

                            Text(
                                text = detailsData.value?.location ?: "",
                                fontSize = 14.sp,
                                color = AppTheme.colors.black70
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_content_spacing))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_phone),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(
                                        end = dimensionResource(
                                            id = R.dimen.spacing_m
                                        )
                                    )
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.date_top_spacing)
                                    ),
                            )

                            Column {
                                for (phone in detailsData.value?.phoneNumbers ?: listOf()) {
                                    Text(
                                        text = phone,
                                        fontSize = 14.sp,
                                        color = AppTheme.colors.black70
                                    )
                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_content_spacing))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mail),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(
                                        end = dimensionResource(
                                            id = R.dimen.spacing_m
                                        )
                                    )
                                    .padding(
                                        vertical = dimensionResource(id = R.dimen.date_top_spacing)
                                    ),
                            )

                            Text(
                                text = stringResource(id = R.string.mail_label),
                                fontSize = 14.sp,
                                color = AppTheme.colors.black70
                            )

                            Text(
                                text = stringResource(id = R.string.mail_post_label),
                                fontSize = 14.sp,
                                color = AppTheme.colors.lightGreen,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height = dimensionResource(id = R.dimen.main_image_height))
                                .padding(top = dimensionResource(id = R.dimen.spacing_l))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.news_item_image),
                                contentDescription = null,
                                modifier = Modifier.width(width = dimensionResource(id = R.dimen.main_image_width)),
                                contentScale = ContentScale.Crop
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = dimensionResource(id = R.dimen.small_content_spacing))
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.cardimage_2),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_xxs))
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.cardimage_3),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xxs))
                                )
                            }
                        }

                        Text(
                            text = detailsData.value?.description ?: "",
                            fontSize = 14.sp,
                            color = AppTheme.colors.black70,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_l))
                        )

                        Text(
                            text = detailsData.value?.subDescription ?: "",
                            fontSize = 14.sp,
                            color = AppTheme.colors.black70,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m))
                        )

                        Text(
                            text = stringResource(id = R.string.link_label),
                            fontSize = 14.sp,
                            color = AppTheme.colors.lightGreen,
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_l))
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .padding(
                                top = dimensionResource(id = R.dimen.people_back_margin),
                            )
                            .fillMaxWidth()
                            .height(
                                height = dimensionResource(id = R.dimen.people_background_height)
                            )
                            .background(color = AppTheme.colors.lightGreyTwo),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier.width(
                                width = dimensionResource(
                                    id = R.dimen.spacing_m
                                )
                            )
                        )

                        for (image in 0..4) {
                            Image(
                                painter = painterResource(id = R.drawable.avatar_1),
                                contentDescription = null,
                                modifier = Modifier.size(size = 36.dp)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.quantity_template),
                            fontSize = 14.sp,
                            color = AppTheme.colors.black60,
                            modifier = Modifier.padding(
                                top = dimensionResource(id = R.dimen.spacing_l),
                                start = dimensionResource(id = R.dimen.small_content_spacing)
                            )
                        )
                    }
                }
            }
        }
    }
}