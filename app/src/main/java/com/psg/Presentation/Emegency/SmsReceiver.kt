package com.psg.Presentation.Emegency

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import androidx.core.content.ContextCompat
import com.psg.Presentation.Util.PreferenceHelper


class SMSReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        if (bundle != null) {
            val pdus = bundle["pdus"] as Array<*>
            for (pdu in pdus) {
                val message = SmsMessage.createFromPdu(pdu as ByteArray, bundle.getString("format"))
                val body = message.messageBody
                val trigger = PreferenceHelper.getTriggerWord(context)
                if (body.contains(trigger, true)) {
                    val serviceIntent = Intent(context, EmergencyService::class.java)
                    ContextCompat.startForegroundService(context, serviceIntent)
                }
            }
        }
    }
}

