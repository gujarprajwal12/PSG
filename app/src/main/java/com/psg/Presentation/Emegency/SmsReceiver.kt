package com.psg.Presentation.Emegency

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import com.psg.Presentation.Util.PreferenceHelper

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle: Bundle? = intent.extras
        val pdus = bundle?.get("pdus") as? Array<*>

        pdus?.forEach { pdu ->
            val message = SmsMessage.createFromPdu(pdu as ByteArray)
            val body = message.messageBody
            val triggerWord = PreferenceHelper.getTriggerWord(context)

            if (body.contains(triggerWord, ignoreCase = true)) {
                val serviceIntent = Intent(context, EmergencyService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(serviceIntent)
                } else {
                    context.startService(serviceIntent)
                }
            }
        }
    }
}
