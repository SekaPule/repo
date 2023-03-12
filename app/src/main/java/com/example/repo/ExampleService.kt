package com.example.repo

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.repo.data.DataProvider
import com.example.repo.data.internet.retrofit.RetrofitClient
import com.example.repo.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class ExampleService : IntentService(EXAMPLE_SERVICE_NAME) {
    private val dataProvider = DataProvider(this)
    private val api = RetrofitClient.configureRetrofit()
    private val repository = Repository(api = api, dataProvider = dataProvider)
    private lateinit var filters: String

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        val newsListJsonFlow = repository.getFilters()

        CoroutineScope(Dispatchers.IO).launch {
            newsListJsonFlow
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