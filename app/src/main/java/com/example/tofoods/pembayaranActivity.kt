package com.example.tofoods

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class pembayaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.konfirmasipembayaran)

        val foodName = intent.getStringExtra("foodName")
        val quantity = intent.getIntExtra("quantity", 0)
        val paymentMethod = intent.getStringExtra("paymentMethod")
        val address = intent.getStringExtra("address")
        val notes = intent.getStringExtra("notes")

        // Mengatur onClickListener untuk tombol kembali
        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Mengatur onClickListener untuk tombol konfirmasi pembayaran
        val konfirmasiButton = findViewById<Button>(R.id.konfirmasiButton)
        konfirmasiButton.setOnClickListener {

            // Navigasi ke halaman konfirmasiPemesanan dan mengirimkan data melalui Intent
            val intent = Intent(this, konfirmasiPemesanan::class.java)
            //intent.putExtra("foodName", foodName)
            intent.putExtra("quantity", quantity)
            intent.putExtra("paymentMethod", paymentMethod)
            intent.putExtra("address", address)
            intent.putExtra("notes", notes)
            startActivity(intent)
        }
    }
}