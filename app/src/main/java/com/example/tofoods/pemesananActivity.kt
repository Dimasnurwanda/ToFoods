package com.example.tofoods

import android.content.Intent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import android.widget.FrameLayout

class pemesananActivity : AppCompatActivity(), AddressUpdateDialog.AddressUpdateListener {
    private lateinit var descriptionTextView: TextView
    private lateinit var addressText: String
    private var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitypemesanan)

        descriptionTextView = findViewById(R.id.descriptionTextView)
        addressText = "Rektorat 201"

        // Temukan view berdasarkan ID-nya
        val backButton: ImageButton = findViewById(R.id.backButton)
        val foodNameTextView: TextView = findViewById(R.id.foodNameTextView)
        val iconImageView: ImageView = findViewById(R.id.iconImageView)
        val textView: TextView = findViewById(R.id.textView)
        val changeMetodeButton = findViewById<Button>(R.id.changeMetodeButton)
        val TextMetode: TextView = findViewById(R.id.TextMetode)
        val changeAddressButton: Button = findViewById(R.id.changeAddressButton)
        val foodTitleTextView: TextView = findViewById(R.id.foodTitleTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val notesIconImageView: ImageView = findViewById(R.id.notesIconImageView)
        val notesTextView: TextView = findViewById(R.id.notesTextView)
        val decreaseButton: Button = findViewById(R.id.decreaseButton)
        val quantityTextView: TextView = findViewById(R.id.quantityTextView)
        val increaseButton: Button = findViewById(R.id.increaseButton)
        val catatanFrame: FrameLayout = findViewById(R.id.catatanFrame)
        val catatanNote: TextView = findViewById(R.id.catatanNote)
        val summaryTitleTextView: TextView = findViewById(R.id.summaryTitleTextView)
        val pesanAntarButton: Button = findViewById(R.id.pesanAntarButton)

        // Setel listener dan aksi yang sesuai di sini, jika diperlukan
        // backButton.setOnClickListener { // Aksi ketika tombol diklik }
        backButton.setOnClickListener {
            // Aksi yang ingin Anda lakukan ketika tombol diklik
            // Contoh: Kembali ke activity sebelumnya
            onBackPressed()
        }

        changeMetodeButton.setOnClickListener {
            showPaymentMethodDialog()
        }

        // changeAddressButton.setOnClickListener { // Aksi ketika tombol diklik }
        changeAddressButton.setOnClickListener(View.OnClickListener {
            // Buka dialog atau aktivitas lain untuk memperbarui alamat
            val dialog = AddressUpdateDialog()
            dialog.show(supportFragmentManager, "address_update_dialog")
        })

        decreaseButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                updateQuantityTextView()
            }
        }

        increaseButton.setOnClickListener {
            quantity++
            updateQuantityTextView()
        }

        // Atur OnClickListener pada catatanFrame
        catatanFrame.setOnClickListener {
            // Munculkan AlertDialog untuk meminta input dari pengguna
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Tambah Catatan")

            // Setup input
            val input = EditText(this)
            builder.setView(input)

            // Set tombol positif "Tambah"
            builder.setPositiveButton("Tambah") { dialog, which ->
                val newNote = input.text.toString()
                val currentNote = catatanNote.text.toString()
                val updatedText = "$currentNote\n$newNote"

                // Atur teks yang sudah diperbarui ke catatanNote
                catatanNote.text = updatedText
            }

            // Set tombol negatif "Batal"
            builder.setNegativeButton("Batal") { dialog, which ->
                dialog.cancel()
            }

            // Tampilkan dialog
            builder.show()
        }

        // pesanAntarButton.setOnClickListener { // Aksi ketika tombol diklik }
        pesanAntarButton.setOnClickListener {
            navigateToOrderConfirmation()
        }

    }

    private fun showPaymentMethodDialog() {
        val paymentMethods = arrayOf("Tunai", "Cash")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih Metode Pembayaran")

        builder.setItems(paymentMethods) { _, which ->
            val selectedMethod = paymentMethods[which]
            updatePaymentMethod(selectedMethod)
        }

        builder.show()
    }

    private fun updatePaymentMethod(selectedMethod: String) {
        val textMetode = findViewById<TextView>(R.id.TextMetode)
        textMetode.text = selectedMethod
    }

    private fun updateQuantityTextView() {
        val quantityTextView = findViewById<TextView>(R.id.quantityTextView)
        quantityTextView.text = quantity.toString()
    }

    override fun onAddressUpdated(newAddress: String) {
        // Update alamat dan tampilkan di descriptionTextView
        addressText = newAddress
        descriptionTextView.text = addressText
    }

    private fun navigateToOrderConfirmation() {
        // Mendapatkan data yang ingin dikirim ke pembayaranActivity
        //val foodName = foodNameTextView.text.toString()
        val quantity = quantity
        val paymentMethod = findViewById<TextView>(R.id.TextMetode).text.toString()
        val address = descriptionTextView.text.toString()
        val notes = findViewById<TextView>(R.id.catatanNote).text.toString()

        // Navigasi ke halaman pembayaranActivity dan mengirimkan data melalui Intent
        val intent = Intent(this, pembayaranActivity::class.java)
        //intent.putExtra("foodName", foodName)
        intent.putExtra("quantity", quantity)
        intent.putExtra("paymentMethod", paymentMethod)
        intent.putExtra("address", address)
        intent.putExtra("notes", notes)
        startActivity(intent)
    }
}