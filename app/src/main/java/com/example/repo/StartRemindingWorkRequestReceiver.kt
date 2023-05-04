package com.example.repo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.repo.data.DonationRemindWorker
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_DATA2
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_JSON
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_KEY_B
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_TITLE
import com.example.repo.data.WorkManagerConfig.UNIQUE_WORK_NAME
import java.util.concurrent.TimeUnit

class StartRemindingWorkRequestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val newsViewString: String? = intent.getStringExtra(NEWS_ITEM_KEY_B)
        val title = intent.getStringExtra(NEWS_ITEM_TITLE)
        val workManager = context.let { WorkManager.getInstance(it) }
        val donateReminder = OneTimeWorkRequestBuilder<DonationRemindWorker>()
            .setInputData(
            workDataOf(
                DETAILS_ITEM_DATA2 to title,
                DETAILS_ITEM_JSON to newsViewString
            )
        )
            .setInitialDelay(30, TimeUnit.MINUTES)

        context.apply {
            NotificationManagerCompat.from(this)
                .cancel(1)
        }

        workManager.beginUniqueWork(
            UNIQUE_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            donateReminder.build()
        ).enqueue()
    }
}
