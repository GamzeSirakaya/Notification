package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlin.concurrent.schedule
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private val channelID = "com.example.notification.channel1"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, name = "Deneme", channelDescription = "deneme1")
        button.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification() {
        val notificationId = 45
        val tapResultIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            tapResultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Demo title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager?.notify(notificationId, notification)

    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) {
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val channel: NotificationChannel = NotificationChannel(id, name, importance).apply {
                description = channelDescription

            }
            notificationManager?.createNotificationChannel(channel)

        }

    }


}