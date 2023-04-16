package com.example.repo.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.auth_feature.di.AuthFeatureDeps
import com.example.categories_feature.di.CategoriesFeatureDeps
import com.example.repo.ExampleService
import com.example.repo.MainActivity
import com.example.repo.di.modules.*
import com.example.repo.presentation.details.views.DetailsScreenFragment
import com.example.repo.presentation.filters.views.FiltersScreenFragment
import com.example.repo.presentation.newslist.view.NewsScreenFragment
import com.example.repo.presentation.profile.views.ProfileScreenFragment
import com.example.search_feature.di.SearchFeatureDeps
import com.example.search_feature.interactor.GetNewsUseCase
import com.example.search_feature.presentation.mapper.NewsViewMapper
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        DatabaseSourceModule::class,
        RemoteSourceModule::class,
        RepoRepositoryBindModule::class,
        FileSourceBindModule::class,
        ContextBindModule::class,
        ModelMapperModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent : AuthFeatureDeps, CategoriesFeatureDeps, SearchFeatureDeps {

    override val viewModelFactory: ViewModelProvider.Factory

    override val newsViewMapper: NewsViewMapper

    override val getNewsUseCase: GetNewsUseCase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(exampleService: ExampleService)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: NewsScreenFragment)
    fun inject(fragment: FiltersScreenFragment)
    fun inject(fragment: ProfileScreenFragment)
    fun inject(fragment: DetailsScreenFragment)
}