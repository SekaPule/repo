package com.example.repo.presentation.newslist.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import com.example.repo.presentation.theme.RepoTheme
import com.example.search_feature.presentation.model.NewsView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsScreenTest {

    private lateinit var viewModel: NewsScreenViewModel

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        viewModel = mock(NewsScreenViewModel::class.java)

        val mockValue = MutableStateFlow(listOf(
            NewsView(
                id = 1,
                category = listOf("Дети"),
                title = "Спонсоры отремонтируют школу-интернат",
                organization = "Благотворительный Фонд «Счастливый Мир»",
                date = "2023-01-27",
                location = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                phoneNumbers = listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
                titleDescription = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                description = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                subDescription = "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
                daysLeftText = "осталось ${
                    "2023-01-27".toLocalDate().let {
                        Clock.System.todayIn(TimeZone.currentSystemDefault()).daysUntil(it)
                    }
                } дней (${"2023-01-27".toLocalDate()})"
            ),
            NewsView(
                id = 2,
                category = listOf("Дети"),
                title = "Спонсоры отремонтируют школу-интернат2",
                organization = "Благотворительный Фонд «Счастливый Мир»",
                date = "2023-01-27",
                location = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                phoneNumbers = listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
                titleDescription = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                description = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                subDescription = "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
                daysLeftText = "осталось ${
                    "2023-01-27".toLocalDate().let {
                        Clock.System.todayIn(TimeZone.currentSystemDefault()).daysUntil(it)
                    }
                } дней (${"2023-01-27".toLocalDate()})"
            )
        ))

        `when`(viewModel.news).thenReturn(mockValue)
    }

    @Test
    fun testLazyColumn_IsDisplayed() {
        rule.setContent {
            RepoTheme {
                NewsScreen(viewModel = viewModel)
            }
        }

        viewModel.news.value.forEach { newsView ->
            rule.onNodeWithTag("NEWS_ITEM_${newsView.id}_TAG")
                .performScrollTo()
                .assertIsDisplayed()
        }
    }
}