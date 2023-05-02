package com.example.repo.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.repo.MainActivity
import com.example.repo.R
import com.example.repo.data.WorkManagerConfig.DETAILS_ACTION
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_DATA2
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_JSON
import com.example.repo.data.WorkManagerConfig.DONATION_REMIND_NOTIFICATION_CHANNEL_ID
import com.example.repo.data.WorkManagerConfig.DONATION_REMIND_NOTIFICATION_CHANNEL_NAME
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_KEY

class DonationRemindWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        val detailsTitle = inputData.getString(DETAILS_ITEM_DATA2) ?: return Result.failure()
        Log.e(this.javaClass.simpleName, "DonRem$detailsTitle")
        setNotification(title = detailsTitle)

        return Result.success()
    }

    private fun setNotification(title: String) {
        NotificationManagerCompat.from(context).notify(2, createNotification(title))
    }

    private fun createNotification(title: String): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    DONATION_REMIND_NOTIFICATION_CHANNEL_ID,
                    DONATION_REMIND_NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

            notificationManager?.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            action = DETAILS_ACTION
            val bundle = bundleOf(
                NEWS_ITEM_KEY to inputData.getString(DETAILS_ITEM_JSON),
            )
            putExtras(bundle)

        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, DONATION_REMIND_NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(title)
            .setContentText(context.getString(R.string.remind_invite_for_donation))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(context.getString(R.string.remind_invite_for_donation))
            )
            .setContentIntent(pendingIntent)

        return builder.build()
    }
}