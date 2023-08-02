package com.example.tofoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tofoods.DB.DBHelper

class RegisterActivity : AppCompatActivity() {

    lateinit var eemail: EditText
    lateinit var epassword: EditText
    lateinit var efullname: EditText
    lateinit var enohp: EditText
    lateinit var btnregister: Button
    lateinit var userDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        eemail = findViewById(R.id.register_email)
        efullname = findViewById(R.id.register_nama)
        enohp = findViewById(R.id.register_notelp)
        epassword = findViewById(R.id.register_password)
        btnregister = findViewById(R.id.btnRegister)
        userDBHelper = DBHelper(this)
    }

    fun registerme(view: View) {
        var iemail = eemail.text.toString()
        var ipassword = epassword.text.toString()
        var ifullname = efullname.text.toString()
        var inohp = enohp.text.toString()

        var cekuser = userDBHelper.cekUser(iemail)
        var status = "Gagal"

        if (cekuser == "0") {
            userDBHelper.RegisterUser(iemail, ipassword, ifullname, inohp)
            status = "Sukses"
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val toast: Toast = Toast.makeText(this, status, Toast.LENGTH_LONG)
        toast.show()
    }
}