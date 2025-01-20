package com.example.mcs_qualification.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mcs_qualification.R
import com.example.mcs_qualification.model.User
import com.example.mcs_qualification.repository.CartRepository
import com.example.mcs_qualification.utils.Core
import com.example.mcs_qualification.utils.UserHelper


class LoginActivity : AppCompatActivity() {
    var inputUsername: EditText? = null
    var inputPassword: EditText? = null
    var btnLogin: Button? = null
    var btnToRegister: Button? = null
    var sendSMSPermission: Int = 0
    var receiveSMSPermission: Int = 0

    private fun initialize() {
        inputUsername = findViewById(R.id.inputUsername)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnToRegister = findViewById(R.id.btnRegister)

        sendSMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        receiveSMSPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)

        if (sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        }

        if (receiveSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialize()

        btnLogin!!.setOnClickListener { x: View? ->
            val helper: UserHelper = UserHelper(this)
            helper.open()

            val user: User? = helper.login(
                inputUsername!!.text.toString(),
                inputPassword!!.text.toString()
            )

            if (user == null) {
                Toast.makeText(
                    this,
                    "Username or password wrong!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Core.user = user
                CartRepository.instance!!.clearItem()
                val intent = Intent(
                    this,
                    MainActivity::class.java
                )
                startActivity(intent)
            }
            helper.close()
        }

        btnToRegister!!.setOnClickListener { x: View? ->
            val intent = Intent(
                this,
                RegisterActivity::class.java
            )
            startActivity(intent)
        }
    }
}