package com.example.mcs_qualification.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mcs_qualification.R
import com.example.mcs_qualification.utils.Core


class SmsReceivedActivity : AppCompatActivity() {
    var lblSetup: TextView? = null
    var lblPunchLine: TextView? = null
    var btnBack: Button? = null

    private fun initialize() {
        lblSetup = findViewById(R.id.lblSetup)
        lblPunchLine = findViewById(R.id.lblPunchline)
        btnBack = findViewById(R.id.btnBack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_received)
        initialize()

        lblPunchLine!!.setText(Core.punchline)
        lblSetup!!.setText(Core.setup)

        btnBack!!.setOnClickListener { x: View? ->
            finish()
        }
    }
}