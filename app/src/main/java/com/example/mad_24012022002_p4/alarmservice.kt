package com.example.mad_24012022002_p4

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class AlarmService : Service() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Create notification channel (Android 8+ requires this for foreground)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "alarm_channel"
            val channel = NotificationChannel(
                channelId,
                "Alarm Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle("Alarm")
                .setContentText("Alarm is ringing...")
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .build()

            startForeground(1, notification)
        }

        // Play sound
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound) // put a file in res/raw
        mediaPlayer.start()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
