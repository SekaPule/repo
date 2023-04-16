package com.example.categories_feature.interactor

import com.example.categories_feature.R
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor() {

    fun execute(): List<Category> = listOf(
        Category(1, R.drawable.icon_kids, "Дети"),
        Category(2, R.drawable.icon_adult, "Взрослые"),
        Category(3, R.drawable.icon_elderly, "Пожилые"),
        Category(4, R.drawable.icon_animals, "Животные"),
        Category(5, R.drawable.icon_event, "Мероприятия"),
    )
}