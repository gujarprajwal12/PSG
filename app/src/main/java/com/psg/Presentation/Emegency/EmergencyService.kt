package com.psg.Presentation.Emegency

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.media.*
import android.os.Build
import android.os.IBinder
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.psg.R

class EmergencyService : Service() {

    companion object {
        const val ACTION_STOP_ALARM = "com.psg.ACTION_STOP_ALARM"
        const val CHANNEL_ID = "emergency_channel"
        const val NOTIFICATION_ID = 1
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var senderNumber: String = ""
    private var ringtone: Ringtone? = null

    private val stopAlarmReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ACTION_STOP_ALARM) {
                stopAlarm()
                stopSelf()
            }
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        registerReceiver(stopAlarmReceiver, IntentFilter(ACTION_STOP_ALARM))
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        senderNumber = intent?.getStringExtra("sender") ?: ""
        startForeground(NOTIFICATION_ID, buildNotification())
        triggerEmergencyActions()
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Emergency Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {
        val stopIntent = Intent(ACTION_STOP_ALARM)
        val stopPendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Emergency Trigger Active")
            .setContentText("Alarm is playing. Tap to stop.")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your own icon
            .addAction(R.drawable.stop, "STOP ALARM", stopPendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun triggerEmergencyActions() {
        playLoudAlarm()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                sendLocationSMS(it.latitude, it.longitude)
            } ?: Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playLoudAlarm() {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Set phone to normal mode and max volume
        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
        audioManager.setStreamVolume(
            AudioManager.STREAM_RING,
            audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),
            0
        )

        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(applicationContext, ringtoneUri)
        ringtone?.play()
    }

    private fun stopAlarm() {
        ringtone?.stop()
        ringtone = null
    }

    private fun sendLocationSMS(latitude: Double, longitude: Double) {
        if (senderNumber.isNotEmpty()) {
            val message = "Emergency! Location: https://maps.google.com/?q=$latitude,$longitude"
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(senderNumber, null, message, null, null)
            Log.d("EmergencyService", "SMS sent to $senderNumber with location.")
        } else {
            Log.e("EmergencyService", "Sender number not provided.")
        }
    }

    override fun onDestroy() {
        stopAlarm()
        unregisterReceiver(stopAlarmReceiver)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}


