package com.example.repo.data

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.repo.MainActivity
import com.example.repo.R
import com.example.repo.StartRemindingWorkRequestReceiver
import com.example.repo.data.WorkManagerConfig.DETAILS_ACTION
import com.example.repo.data.WorkManagerConfig.DETAILS_ITEM_JSON
import com.example.repo.data.WorkManagerConfig.DETAILS_TITLE
import com.example.repo.data.WorkManagerConfig.DONATION_NOTIFICATION_CHANNEL_ID
import com.example.repo.data.WorkManagerConfig.DONATION_NOTIFICATION_CHANNEL_NAME
import com.example.repo.data.WorkManagerConfig.DONATION_VALUE
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_KEY_A
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_KEY_B
import com.example.repo.data.WorkManagerConfig.NEWS_ITEM_TITLE
import com.example.repo.data.WorkManagerConfig.REMIND_ACTION

class DonationWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val detailsTitle = inputData.getString(DETAILS_TITLE) ?: return Result.failure()
        val donationValue = inputData.getString(DONATION_VALUE) ?: return Result.failure()
        setNotification(title = detailsTitle, text = donationValue)

        return Result.success()
    }

    private fun setNotification(title: String, text: String) {
        NotificationManagerCompat.from(context).notify(1, createNotification(title, text))
    }

    private fun createNotification(title: String, text: String): Notification {
        val newsViewJson = inputData.getString(DETAILS_ITEM_JSON)

        val channel = NotificationChannel(
                DONATION_NOTIFICATION_CHANNEL_ID,
                DONATION_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        notificationManager?.createNotificationChannel(channel)

        val intent = Intent(context, MainActivity::class.java).apply {
            action = DETAILS_ACTION
            val bundle = bundleOf(
                NEWS_ITEM_KEY_A to newsViewJson
            )
            putExtras(bundle)
        }

        val pendingIntent = PendingIntent.getActivity(
            context, 0,
            intent, PendingIntent.FLAG_CANCEL_CURRENT
        )

        val snoozeIntent = Intent(context, StartRemindingWorkRequestReceiver::class.java).apply {
            action = REMIND_ACTION
            val bundle = bundleOf(
                NEWS_ITEM_KEY_B to newsViewJson,
                NEWS_ITEM_TITLE to title
            )
            putExtras(bundle)
        }

        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, DONATION_NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(title)
            .setContentText(
                context.getString(R.string.thanks_for_donation) + text + context.getString(
                    R.string.invite_for_donate
                )
            )
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        context.getString(R.string.thanks_for_donation) + text + context.getString(
                            R.string.invite_for_donate
                        )
                    )
            )
            .addAction(
                NotificationCompat.Action(
                    R.drawable.ic_calendar_grey,
                    context.getString(R.string.remind_later_btn_text),
                    snoozePendingIntent
                )
            )
            .setContentIntent(pendingIntent)

        return builder.build()
    }
}