package com.example.repo.presentation.base.mapper

import com.example.data.model.News
import com.example.search_feature.presentation.model.NewsView
import kotlinx.datetime.*
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*

internal class NewsViewMapperImplTest {

    private val mapper = NewsViewMapperImpl()


    @Test
    fun mapFromDomainModelTest_returnDomainModel() {
        val testModel = News(
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
        )

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
            daysLeftText = "осталось ${
                "2023-01-27".toLocalDate().let {
                    Clock.System.todayIn(TimeZone.currentSystemDefault()).daysUntil(it)
                }
            } дней (${"2023-01-27".toLocalDate()})"
        )
        val actual = mapper.mapFromDomainModel(testModel)

        assertEquals(expected, actual)
    }
}