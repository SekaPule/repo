package com.example.search_feature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.search_feature.interactor.GetNewsUseCase
import com.example.search_feature.presentation.mapper.NewsViewMapper
import com.example.search_feature.presentation.views.SearchScreenFragment
import com.example.search_feature.presentation.views.viewpager.page.ViewPagerEventFragment
import com.example.search_feature.presentation.views.viewpager.page.ViewPagerNKOFragment
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@SearchFeature
@Component(dependencies = [SearchFeatureDeps::class])
internal interface SearchFeatureComponent {

    fun inject(fragment: SearchScreenFragment)
    fun inject(fragment: ViewPagerEventFragment)
    fun inject(fragment: ViewPagerNKOFragment)

    @Component.Builder
    interface Builder {

        fun build(): SearchFeatureComponent

        fun deps(deps: SearchFeatureDeps): Builder
    }
}

interface SearchFeatureDeps {

    val viewModelFactory: ViewModelProvider.Factory

    val getNewsUseCase: GetNewsUseCase

    val newsViewMapper: NewsViewMapper
}

interface SearchFeatureDepsProvider {

    val deps: SearchFeatureDeps

    companion object : SearchFeatureDepsProvider by SearchFeatureDepsStore
}

object SearchFeatureDepsStore : SearchFeatureDepsProvider {

    override var deps: SearchFeatureDeps by notNull()
}

internal class SearchFeatureComponentViewModel : ViewModel() {

    val searchFeatureComponent =
        DaggerSearchFeatureComponent.builder().deps(SearchFeatureDepsProvider.deps).build()
}

@Scope
annotation class SearchFeature