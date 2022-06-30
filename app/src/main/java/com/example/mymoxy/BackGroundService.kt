package com.example.mymoxy

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.math.log

class BackGroundService : Service() {


    override fun onCreate() {
        super.onCreate()
        createChannel()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var extras = intent?.extras
        val command = extras?.getInt(SERVICE_TASK) ?: START_SERVICE

        when (command) {
            STOP_SERVICE -> stopSelf()
        }

        Thread(Runnable {
            while (true) {
                Log.d("Flex", "BackGroundService")
                Thread.sleep(2000)
            }
        }).start()
        createChannel()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "BackGroundService",
                "BackGroundService",
                NotificationManager.IMPORTANCE_MIN
            )
            channel.enableLights(true)
            channel.enableVibration(true)
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
            setNotification()
        }
    }

    private fun setNotification() {
        val builder = NotificationCompat.Builder(this, "BackGroundService")
            .setOngoing(true)
            .setContentTitle("BackGroundService")
            .setSmallIcon(R.mipmap.ic_launcher)
            .addAction(getNotificationStopAction())

        startForeground(1, builder.build())
    }

    private fun getNotificationStopAction(): NotificationCompat.Action {
        val intent = Intent(this, BackGroundService::class.java).apply {
            putExtra(SERVICE_TASK, STOP_SERVICE)
        }
        val stopPendingIntent = PendingIntent.getService(
            this, STOP_SERVICE, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Action(0, "stop", stopPendingIntent)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        private const val SERVICE_TASK = "SERVICE_TASK"
        private const val START_SERVICE = 100
        private const val STOP_SERVICE = 101
        private const val STOP_CHECK = 101
    }
}