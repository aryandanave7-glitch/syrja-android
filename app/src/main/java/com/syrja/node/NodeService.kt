package com.syrja.node

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import java.io.OutputStream
import java.net.Socket
import kotlin.concurrent.thread

class NodeService : Service() {

    private var socket: Socket? = null
        private val desktopIp = "192.168.0.147" // FIXME: Replace this with your actual Laptop IP!
            private val port = 8080

            override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
                createNotificationChannel()

                val notification = NotificationCompat.Builder(this, "SYRJA_NODE")
                .setContentTitle("Syrja Node Active")
                .setContentText("Attempting to connect to Desktop...")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .build()

                startForeground(1, notification)

                // Start the Networking Thread
                connectToDesktop()

                return START_STICKY
            }

            private fun connectToDesktop() {
                thread {
                    try {
                        Log.d("Syrja", "Connecting to $desktopIp:$port...")
                        socket = Socket(desktopIp, port)
                        val output: OutputStream = socket!!.getOutputStream()

                        // Send a "Hello" JSON packet
                        val helloJson = """{"type": "connection", "device": "${Build.MODEL}", "status": "online"}"""
                        output.write(helloJson.toByteArray())
                        output.flush()

                        Log.d("Syrja", "Handshake sent successfully!")
                    } catch (e: Exception) {
                        Log.e("Syrja", "Connection failed: ${e.message}")
                        // In the next step, we'll add a "Retry" loop here
                    }
                }
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

            override fun onDestroy() {
                try { socket?.close() } catch (e: Exception) {}
                super.onDestroy()
            }

            override fun onBind(intent: Intent?): IBinder? = null
}
