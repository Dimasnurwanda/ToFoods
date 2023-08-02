package com.example.tofoods

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class konfirmasiPemesanan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.konfirmasipemesanan)

        // Mendapatkan data dari Intent
        //val foodName = intent.getStringExtra("foodName")
        val quantity = intent.getIntExtra("quantity", 0)
        val paymentMethod = intent.getStringExtra("paymentMethod")
        val address = intent.getStringExtra("address")
        val notes = intent.getStringExtra("notes")

        // Menampilkan data pada TextView di konfirmasiPemesanan.xml
        val quantityTextView = findViewById<TextView>(R.id.hasilquantityTextView)
        val paymentMethodTextView = findViewById<TextView>(R.id.HasilTextMetode)
        val addressTextView = findViewById<TextView>(R.id.hasildescriptionTextView)
        val notesTextView = findViewById<TextView>(R.id.hasilcatatanNote)

        //foodNameTextView.text = "Nama Makanan: $foodName"
        quantityTextView.text = "$quantity"
        paymentMethodTextView.text = "$paymentMethod"
        addressTextView.text = "$address"
        notesTextView.text = "$notes"

        val konfirmasiButton = findViewById<Button>(R.id.konfirmasiButton)
        konfirmasiButton.setOnClickListener {
            // Navigasi kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Tampilkan Toast sebagai pemberitahuan
            Toast.makeText(this, "Pesanan Kamu berhasil dikonfirmasi!", Toast.LENGTH_SHORT).show()
        }
    }
}