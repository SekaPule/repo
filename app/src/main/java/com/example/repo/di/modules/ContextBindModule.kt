package com.example.repo.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ContextBindModule {

    @Singleton
    @Binds
    fun bindContext(application: Application): Context
}