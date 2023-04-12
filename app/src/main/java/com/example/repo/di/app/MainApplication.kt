package com.example.repo.di.app

import android.app.Application
import com.example.repo.di.AppComponent
import com.example.repo.di.DaggerAppComponent

class MainApplication : Application() {

    init {
        instance = this
    }

    var appComponent: AppComponent =
        DaggerAppComponent.builder().application(this).build()


    companion object {
        var instance: MainApplication? = null
        fun appComponent(): AppComponent {
            return instance!!.appComponent
        }
    }
}