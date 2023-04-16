package com.example.repo.di.app

import android.app.Application
import com.example.auth_feature.di.AuthFeatureDepsStore
import com.example.categories_feature.di.CategoriesFeatureDepsStore
import com.example.repo.di.AppComponent
import com.example.repo.di.DaggerAppComponent
import com.example.search_feature.di.SearchFeatureDepsStore

class MainApplication : Application() {

    init {
        instance = this
    }

    var appComponent: AppComponent =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        appComponent().also { appComponent ->
            CategoriesFeatureDepsStore.deps = appComponent
            AuthFeatureDepsStore.deps = appComponent
            SearchFeatureDepsStore.deps = appComponent
        }
    }


    companion object {
        var instance: MainApplication? = null
        fun appComponent(): AppComponent {
            return instance!!.appComponent
        }
    }
}