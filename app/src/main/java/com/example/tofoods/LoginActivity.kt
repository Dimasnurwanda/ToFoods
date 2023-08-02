package com.example.tofoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tofoods.DB.DBHelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        if (savedLogin.getString("Status", "Off") == "On") {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val editEmail: EditText = findViewById(R.id.login_email)
        val editPassword: EditText = findViewById(R.id.login_password)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val registerText: TextView = findViewById(R.id.txtRegister)
        val userDBHelper = DBHelper(this)

        btnLogin.setOnClickListener() {
            val emailku = editEmail.text.toString()
            val passku = editPassword.text.toString()
            val cekLogin = userDBHelper.cekLogin(emailku, passku)

            if (cekLogin == "1") {
                editSavedLogin.putString("Email", emailku)
                editSavedLogin.putString("Password", passku)
                editSavedLogin.putString("Status", "On")
                editSavedLogin.commit()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val toast: Toast = Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_LONG)
                toast.show()
            }
        }

        registerText.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}