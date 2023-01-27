package com.example.repo

import android.app.IntentService
import android.content.Intent
import com.example.repo.data.DataProvider


class ExampleService : IntentService(EXAMPLE_SERVICE_NAME) {
    private val dataProvider = DataProvider(this)
    private lateinit var filters: String

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        Thread.sleep(5000)
        try {
            filters = dataProvider.getFilterItemsFromAssetsJson()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Intent().also {
            it.action = ACTION_MY_INTENT_SERVICE
            it.putExtra(EXTRA_KEY_OUT, filters)
            sendBroadcast(it)
        }
    }

    companion object {
        const val ACTION_MY_INTENT_SERVICE = "com.example.repo.RESPONSE"
        private const val EXTRA_KEY_OUT = "EXTRA_OUT"
        private const val EXAMPLE_SERVICE_NAME = "ExampleService"
    }
}