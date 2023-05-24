package com.example.repo.presentation.details.views

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.MutableLiveData
import androidx.test.platform.app.InstrumentationRegistry
import com.example.repo.presentation.details.vm.DetailsScreenViewModel
import com.example.repo.presentation.theme.RepoTheme
import com.example.search_feature.presentation.model.NewsView
import kotlinx.datetime.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DetailsScreenTest {

    private lateinit var viewModel: DetailsScreenViewModel

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        viewModel =
            spy(DetailsScreenViewModel(context = InstrumentationRegistry.getInstrumentation().context))

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

        `when`(viewModel.detailsData).thenReturn(mockData)
    }

    @Test
    fun testShowDonationDialog_IsDisplayed() {
        rule.setContent {
            RepoTheme {
                DetailsScreen(viewModel = viewModel)
            }
        }

        rule.onNodeWithTag("HELP_WITH_MONEY_BTN_TAG").performClick()

        rule.waitUntil {
            rule
                .onAllNodesWithTag("HELP_MONEY_DIALOG_TAG")
                .fetchSemanticsNodes()
                .size == 1
        }
    }
}