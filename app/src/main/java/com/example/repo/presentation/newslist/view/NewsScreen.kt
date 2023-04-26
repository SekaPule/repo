package com.example.repo.presentation.newslist.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.repo.R
import com.example.repo.presentation.newslist.viewmodel.NewsIntent
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import com.example.repo.presentation.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel
) {
    val newsList = viewModel.news.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.news_title),
                        style = AppTheme.typography.textStyle2
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = AppTheme.colors.white,
                    containerColor = AppTheme.colors.lightGreen,
                    actionIconContentColor = AppTheme.colors.white
                ),
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                viewModel.obtainIntent(NewsIntent.FilterNewsIntent)
                            }
                            .padding(end = dimensionResource(id = R.dimen.spacing_xs))
                    )
                }
            )
        },
        containerColor = AppTheme.colors.lightGreyTwo
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding), contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            items(newsList.value) { newsItem ->
                NewsComponent(newsView = newsItem, onClick = {
                    newsItem.isChecked = true
                    viewModel.obtainIntent(NewsIntent.DetailsNewsIntent(newsView = newsItem))
                })
            }
        }
    }
}