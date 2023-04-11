package com.example.repo.di

import android.app.Application
import com.example.repo.ExampleService
import com.example.repo.MainActivity
import com.example.repo.di.modules.*
import com.example.repo.presentation.auth.views.AuthScreenFragment
import com.example.repo.presentation.categorieslist.views.CategoriesScreenFragment
import com.example.repo.presentation.details.views.DetailsScreenFragment
import com.example.repo.presentation.filters.views.FiltersScreenFragment
import com.example.repo.presentation.newslist.view.NewsScreenFragment
import com.example.repo.presentation.profile.views.ProfileScreenFragment
import com.example.repo.presentation.search.views.SearchScreenFragment
import com.example.repo.presentation.search.views.viewpager.page.ViewPagerEventFragment
import com.example.repo.presentation.search.views.viewpager.page.ViewPagerNKOFragment
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
        ModelViewMapperModule::class,
        ModelViewMapperBindModule::class,
        ModelMapperModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(exampleService: ExampleService)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: AuthScreenFragment)
    fun inject(fragment: NewsScreenFragment)
    fun inject(fragment: FiltersScreenFragment)
    fun inject(fragment: CategoriesScreenFragment)
    fun inject(fragment: ProfileScreenFragment)
    fun inject(fragment: DetailsScreenFragment)
    fun inject(fragment: ViewPagerNKOFragment)
    fun inject(fragment: ViewPagerEventFragment)
    fun inject(fragment: SearchScreenFragment)
}