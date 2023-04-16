package com.example.repo

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.data.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class ExampleService : IntentService(EXAMPLE_SERVICE_NAME) {

    @Inject
    lateinit var repoRepository: RepoRepository

    init {
        appComponent().inject(this)
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        CoroutineScope(Dispatchers.IO).launch {
            repoRepository.getFilters()
                .flowOn(Dispatchers.IO)
                .collect { json ->
                    Log.e("BTHREAD2", Thread.currentThread().name)
                    Intent().also {
                        it.action = ACTION_MY_INTENT_SERVICE
                        it.putExtra(EXTRA_KEY_OUT, json)
                        sendBroadcast(it)
                    }
                }
        }

    }

    companion object {
        const val ACTION_MY_INTENT_SERVICE = "com.example.repo.RESPONSE"
        private const val EXTRA_KEY_OUT = "EXTRA_OUT"
        private const val EXAMPLE_SERVICE_NAME = "ExampleService"
    }
}