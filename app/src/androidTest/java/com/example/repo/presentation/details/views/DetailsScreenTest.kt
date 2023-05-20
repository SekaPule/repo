package com.example.repo.presentation.details.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import com.example.repo.presentation.details.vm.DetailsScreenViewModel
import com.example.repo.presentation.details.vm.DetailsViewState
import com.example.repo.presentation.theme.RepoTheme
import com.example.search_feature.presentation.model.NewsView
import kotlinx.datetime.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.concurrent.thread

internal class DetailsScreenTest {

    private lateinit var viewModel: DetailsScreenViewModel

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        viewModel = mock(DetailsScreenViewModel::class.java)

        val mockData = MutableLiveData(
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
            )
        )

        `when`(viewModel.viewState).thenReturn(MutableLiveData(DetailsViewState()))
        `when`(viewModel.detailsData).thenReturn(mockData)
    }

    @Test
    fun testShowDonationDialog_IsDisplayed() {
        rule.setContent {
            RepoTheme {
                DetailsScreen(viewModel = viewModel)
            }
        }

        rule.onNodeWithText("Помочь деньгами").performClick()
        thread {
            Thread.sleep(2000)
            rule.onNodeWithText("Перевести").assertIsDisplayed()
        }
    }
}