package com.syrja.node

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.os.Build
import androidx.core.app.NotificationCompat

class NodeService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        // This notification keeps the app alive in the background
        val notification = NotificationCompat.Builder(this, "SYRJA_NODE")
        .setContentTitle("Syrja Node Active")
        .setContentText("Maintaining connection to Desktop...")
        .setSmallIcon(android.R.drawable.stat_notify_sync)
        .build()

        // Start as foreground service
        startForeground(1, notification)

        return START_STICKY // Tells Android to restart the service if it ever crashes
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "SYRJA_NODE", "Syrja Background Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
