package com.example.mcs_qualification.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mcs_qualification.R
import com.example.mcs_qualification.utils.UserHelper

class RegisterActivity : AppCompatActivity() {
    var inputUsername: EditText? = null
    var inputEmail: EditText? = null
    var inputPassword: EditText? = null
    var inputConfirmPassword: EditText? = null
    var radioMale: RadioButton? = null
    var radioFemale: RadioButton? = null
    var btnRegister: Button? = null
    var btnToLogin: Button? = null

    private fun initialize() {
        inputUsername = findViewById(R.id.inputUsername)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword)
        radioFemale = findViewById(R.id.radioFemale)
        radioMale = findViewById(R.id.radioMale)
        btnRegister = findViewById(R.id.btnRegister)
        btnToLogin = findViewById(R.id.btnLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initialize()

        btnRegister!!.setOnClickListener { x: View? ->
            if (inputConfirmPassword!!.text.toString() != inputPassword!!.text
                    .toString()
            ) {
                Toast.makeText(
                    this,
                    "Password and Confirm password must be same!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val helper: UserHelper = UserHelper(this)
            helper.open()

            var gender = 0
            if (radioFemale!!.isChecked) gender = 1

            helper.register(
                inputUsername!!.text.toString(),
                inputPassword!!.text.toString(),
                inputEmail!!.text.toString(),
                gender
            )
            helper.close()

            val intent = Intent(
                this,
                LoginActivity::class.java
            )
            startActivity(intent)
        }

        btnToLogin!!.setOnClickListener { x: View? ->
            val intent = Intent(
                this,
                LoginActivity::class.java
            )
            startActivity(intent)
        }
    }
}