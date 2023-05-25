package com.example.categories_feature.presentation.vm

import com.example.categories_feature.interactor.Category
import com.example.categories_feature.interactor.GetCategoriesUseCase
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class CategoriesScreenViewModelTest {

    private val getCategoriesUseCase = mock<GetCategoriesUseCase>()

    @Test
    fun getCategories_oneElement_returnOneElementList() {

        val testCategoriesData = listOf(
            Category(1,1,"Дети")
        )

        Mockito.`when`(getCategoriesUseCase.execute()).thenReturn(testCategoriesData)

        val actual = getCategoriesUseCase.execute()
        val expected = listOf(
            Category(1,1,"Дети")
        )

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun getCategories_SeveralElements_returnSeveralElementsList() {

        val testCategoriesData = listOf(
            Category(1, 1, "Дети"),
            Category(2, 1, "Взрослые"),
            Category(3, 1, "Пожилые"),
            Category(4, 1, "Животные"),
            Category(5, 1, "Мероприятия"),
        )

        Mockito.`when`(getCategoriesUseCase.execute()).thenReturn(testCategoriesData)

        val actual = getCategoriesUseCase.execute()
        val expected = listOf(
            Category(1, 1, "Дети"),
            Category(2, 1, "Взрослые"),
            Category(3, 1, "Пожилые"),
            Category(4, 1, "Животные"),
            Category(5, 1, "Мероприятия"),
        )

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun getCategories_EmptyList_returnEmptyList() {

        val testCategoriesData = emptyList<Category>()

        Mockito.`when`(getCategoriesUseCase.execute()).thenReturn(testCategoriesData)

        val actual = getCategoriesUseCase.execute()
        val expected = emptyList<Category>()

        Assertions.assertEquals(expected, actual)
    }
}