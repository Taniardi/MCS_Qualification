package com.example.mcs_qualification.activity

import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mcs_qualification.R
import org.json.JSONException

class JokesActivity : AppCompatActivity() {
    var lblSetup: TextView? = null
    var lblPunchLine: TextView? = null
    var btnSendToFriend: Button? = null
    var btnBack: Button? = null
    var inputPhoneNumber: EditText? = null
    var smsManager: SmsManager? = null

    private fun initialize() {
        lblSetup = findViewById(R.id.lblSetup)
        lblPunchLine = findViewById(R.id.lblPunchline)
        btnSendToFriend = findViewById(R.id.btnSendToFriend)
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber)
        btnBack = findViewById(R.id.btnBack)

        smsManager = SmsManager.getDefault()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)

        initialize()

        val requestQueue = Volley.newRequestQueue(this)
        val url = "https://official-joke-api.appspot.com/random_joke"
        val jsonArrayRequest = JsonObjectRequest(url,
            { response ->
                try {
                    lblSetup!!.text = response.getString("setup")
                    lblPunchLine!!.text = response.getString("punchline")
                } catch (e: JSONException) {
                }
            }, { })

        requestQueue.add(jsonArrayRequest)

        btnSendToFriend!!.setOnClickListener { x: View? ->
            if (inputPhoneNumber!!.text.toString() === "") return@setOnClickListener
            smsManager!!.sendTextMessage(
                inputPhoneNumber!!.text.toString(),
                null,
                """
                    ${lblSetup!!.text}
                    ${lblPunchLine!!.text}
                    """.trimIndent(),
                null,
                null
            )
            Toast.makeText(this, "Successfully sended!", Toast.LENGTH_SHORT).show()
        }

        btnBack!!.setOnClickListener { x: View? ->
            finish()
        }
    }
}