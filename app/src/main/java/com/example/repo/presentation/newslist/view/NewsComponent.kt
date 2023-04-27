package com.example.repo.presentation.newslist.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repo.R
import com.example.repo.presentation.theme.AppTheme
import com.example.search_feature.presentation.model.NewsView

@Composable
fun NewsComponent(
    newsView: NewsView,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.white
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.news_item_image),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(all = dimensionResource(id = R.dimen.spacing_xxs))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                if (!newsView.isChecked) {
                    Row(
                        modifier = Modifier
                            .size(size = 20.dp)
                            .background(color = AppTheme.colors.maccaronyAndCheese)
                    ) {}
                }
            }

            Text(
                text = newsView.title ?: "",
                style = AppTheme.typography.textStyle6,
                color = AppTheme.colors.blueGrey,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.ic_decor),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = dimensionResource(id = R.dimen.spacing_xs))
            )

            Text(
                text = newsView.titleDescription ?: "",
                fontSize = 14.sp,
                color = AppTheme.colors.black70,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = 0.dp,
                    top = dimensionResource(id = R.dimen.small_content_spacing),
                    end = 0.dp,
                    bottom = dimensionResource(id = R.dimen.spacing_m)
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = AppTheme.colors.turtleGreen),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.small_content_spacing
                        ),
                        vertical = dimensionResource(id = R.dimen.date_top_spacing)
                    )
                )

                Text(
                    text = newsView.daysLeftText ?: "",
                    fontSize = 12.sp,
                    color = AppTheme.colors.white
                )
            }
        }
    }
}