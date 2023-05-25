package com.example.repo.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineDispatchersModule {

    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher =
        Dispatchers.IO
}