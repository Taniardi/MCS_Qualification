package com.example.mcs_qualification.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import com.example.mcs_qualification.activity.SmsReceivedActivity
import com.example.mcs_qualification.utils.Core

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == null) {
            Log.d("info", "Null object")
        } else if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras

            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                val smsMessage = arrayOfNulls<SmsMessage>(
                    pdus!!.size
                )

                for (i in pdus.indices) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        val format = bundle.getString("format")
                        smsMessage[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
                    } else {
                        smsMessage[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                    }
                }

                Core.setup =
                    smsMessage[0]!!.messageBody.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[0]
                Core.punchline =
                    smsMessage[0]!!.messageBody.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[1]

                val intent2 = Intent(
                    context,
                    SmsReceivedActivity::class.java
                )
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent2)
            }
        }
    }
}