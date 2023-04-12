package com.example.repo.presentation.categorieslist.vm

import androidx.lifecycle.ViewModel
import com.example.repo.domain.interactor.GetCategoriesUseCase
import com.example.repo.domain.model.Category
import javax.inject.Inject

class CategoriesScreenViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    fun getCategories(): List<Category> = getCategoriesUseCase.execute()
}