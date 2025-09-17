package com.example.mad_24012022002_p4

import android.app.*
import android.content.*
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import java.util.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btn = Button(this).apply { text = "Set Alarm (5s)" }
        setContentView(btn)

        btn.setOnClickListener {
            val alarmMgr = getSystemService(ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java)
            val pi = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

            // Fire after 5 seconds
            alarmMgr.setExact(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 5000,
                pi
            )
        }
    }
}