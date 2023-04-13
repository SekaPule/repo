package com.example.categories_feature.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.categories_feature.interactor.Category
import com.example.categories_feature.interactor.GetCategoriesUseCase
import javax.inject.Inject

class CategoriesScreenViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    fun getCategories(): List<Category> = getCategoriesUseCase.execute()
}