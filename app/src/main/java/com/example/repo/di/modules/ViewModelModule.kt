package com.example.repo.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.repo.presentation.auth.vm.AuthScreenViewModel
import com.example.repo.presentation.categorieslist.vm.CategoriesScreenViewModel
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import com.example.repo.presentation.search.vm.SearchScreenViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsScreenViewModel::class)
    abstract fun bindNewsScreenViewModel(view: NewsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthScreenViewModel::class)
    abstract fun bindAuthScreenViewModel(view: AuthScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesScreenViewModel::class)
    abstract fun bindCategoriesScreenViewModel(view: CategoriesScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchScreenViewModel::class)
    abstract fun bindSearchScreenViewModel(view: SearchScreenViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull()

            ?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}