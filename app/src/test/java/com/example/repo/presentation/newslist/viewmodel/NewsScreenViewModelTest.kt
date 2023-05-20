package com.example.repo.presentation.newslist.viewmodel

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.data.model.News
import com.example.repo.domain.interactor.InitDataForCurrentSessionUseCase
import com.example.repo.presentation.base.mapper.NewsViewMapperImpl
import com.example.search_feature.interactor.GetNewsUseCase
import com.example.search_feature.presentation.model.NewsView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.mock

internal class NewsScreenViewModelTest {

    private val newsViewMapper = NewsViewMapperImpl()
    private val getNewsUseCase = mock(GetNewsUseCase::class.java)
    private val initDataForCurrentSessionUseCase =
        mock(InitDataForCurrentSessionUseCase::class.java)

    private lateinit var viewModel: NewsScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun afterEach() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun beforeEach() {
        viewModel = NewsScreenViewModel(
            getNewsUseCase = getNewsUseCase,
            initDataForCurrentSessionUseCase = initDataForCurrentSessionUseCase,
            newsViewMapper = newsViewMapper
        )
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

        })
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun obtainIntent_DetailsNewsIntent_setIntentData() {
        viewModel.openDetails.observeForever {}

        val testNewsData = NewsView(
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
            daysLeftText = "осталось 30 дней (2023-01-27)"
        )

        val testNewsIntent = NewsIntent.DetailsNewsIntent(newsView = testNewsData)
        viewModel.obtainIntent(testNewsIntent)

        val expected = NewsView(
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
            daysLeftText = "осталось 30 дней (2023-01-27)"
        )
        val actual = viewModel.openDetails.value

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun filterNews_returnFilteredNews() {
        viewModel.openDetails.observeForever {}

        val testFilters = listOf("Дети")
        val testNewsList = listOf(
            News(
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
            ),
            News(
                id = 1,
                category = listOf("Взрослые"),
                title = "Спонсоры отремонтируют школу-интернат",
                organization = "Благотворительный Фонд «Счастливый Мир»",
                date = "2023-01-27",
                location = "Санкт-Петербург, Кирочная улица, д. 50А, каб. 208",
                phoneNumbers = listOf("+7 (937) 037 37-73", "+7 (937) 016 16-16"),
                titleDescription = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                description = "Участники и болельщики смогли весело и активно провести время на «Петербургском благотворительном марафоне» и при этом финансово поучаствовать в помощи детям.",
                subDescription = "При этом финансово поучаствовать в помощи детям. При этом финансово поучаствовать в помощи детям.",
            )
        )

        Mockito.`when`(getNewsUseCase.execute()).thenReturn(flow {
            emit(testNewsList)
        })

        viewModel.initNews()
        viewModel.filterNews(filters = testFilters)

        val expected = listOf(
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

        Assertions.assertEquals(expected, viewModel.news.value)
    }
}