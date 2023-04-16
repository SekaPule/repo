package com.example.auth_feature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.auth_feature.presentation.views.AuthScreenFragment
import dagger.Component
import javax.inject.Scope
import kotlin.properties.Delegates.notNull

@AuthFeature
@Component(dependencies = [AuthFeatureDeps::class])
internal interface AuthFeatureComponent {

    fun inject(fragment: AuthScreenFragment)

    @Component.Builder
    interface Builder {

        fun build(): AuthFeatureComponent

        fun deps(deps: AuthFeatureDeps): Builder
    }
}

interface AuthFeatureDeps {

    val viewModelFactory: ViewModelProvider.Factory
}

interface AuthFeatureDepsProvider {

    val deps: AuthFeatureDeps

    companion object : AuthFeatureDepsProvider by AuthFeatureDepsStore
}

object AuthFeatureDepsStore : AuthFeatureDepsProvider {

    override var deps: AuthFeatureDeps by notNull()
}

internal class AuthFeatureComponentViewModel : ViewModel() {

    val authFeatureComponent =
        DaggerAuthFeatureComponent.builder().deps(AuthFeatureDepsProvider.deps).build()
}

@Scope
annotation class AuthFeature