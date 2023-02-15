package com.example.repo

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.repo.data.DataProvider
import io.reactivex.rxjava3.core.Single


class ExampleService : IntentService(EXAMPLE_SERVICE_NAME) {
    private val dataProvider = DataProvider(this)
    private lateinit var filters: String

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {

        val newsListJsonReactive = Single.create { emitter ->
            Log.e("BTHREAD1", Thread.currentThread().name)
            emitter.onSuccess(dataProvider.getFilterItemsFromAssetsJson())
        }

        newsListJsonReactive
            .subscribe({ json ->
                Log.e("BTHREAD2", Thread.currentThread().name)
                Intent().also {
                    it.action = ACTION_MY_INTENT_SERVICE
                    it.putExtra(EXTRA_KEY_OUT, json)
                    sendBroadcast(it)
                }
            }, { error ->
                Log.e("TAG", "$error")
            })

    }

    companion object {
        const val ACTION_MY_INTENT_SERVICE = "com.example.repo.RESPONSE"
        private const val EXTRA_KEY_OUT = "EXTRA_OUT"
        private const val EXAMPLE_SERVICE_NAME = "ExampleService"
    }
}