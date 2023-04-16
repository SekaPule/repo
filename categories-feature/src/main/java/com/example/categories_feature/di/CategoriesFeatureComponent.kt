package com.example.categories_feature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categories_feature.presentation.views.CategoriesScreenFragment
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@CategoriesFeature
@Component(dependencies = [CategoriesFeatureDeps::class])
internal interface CategoriesFeatureComponent {

    fun inject(fragment: CategoriesScreenFragment)

    @Component.Builder
    interface Builder {

        fun build(): CategoriesFeatureComponent

        fun deps(deps: CategoriesFeatureDeps): Builder
    }
}

interface CategoriesFeatureDeps {

    val viewModelFactory: ViewModelProvider.Factory
}

interface CategoriesFeatureDepsProvider {

    val deps: CategoriesFeatureDeps

    companion object : CategoriesFeatureDepsProvider by CategoriesFeatureDepsStore
}

object CategoriesFeatureDepsStore : CategoriesFeatureDepsProvider {

    override var deps: CategoriesFeatureDeps by notNull()
}

internal class CategoriesFeatureComponentViewModel : ViewModel() {

    val categoriesFeatureComponent =
        DaggerCategoriesFeatureComponent.builder().deps(CategoriesFeatureDepsProvider.deps).build()
}

@Scope
annotation class CategoriesFeature