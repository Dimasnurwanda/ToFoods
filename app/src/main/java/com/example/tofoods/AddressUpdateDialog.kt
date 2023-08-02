package com.example.tofoods

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddressUpdateDialog : DialogFragment() {

    interface AddressUpdateListener {
        fun onAddressUpdated(newAddress: String)
    }

    private lateinit var addressUpdateListener: AddressUpdateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            addressUpdateListener = context as AddressUpdateListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement AddressUpdateListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Inflate tampilan dialog dari XML yang sudah dibuat sebelumnya
        val view = requireActivity().layoutInflater.inflate(R.layout.fragment_address_update, null)

        // Temukan view yang ada di dalam tampilan dialog
        val addressEditText = view.findViewById<EditText>(R.id.addressEditText)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)

        // Setel click listener untuk tombol "Simpan"
        saveButton.setOnClickListener {
            // Tangani perubahan alamat di sini, misalnya dengan menyimpan alamat baru ke database
            // atau mengupdate tampilan alamat di aktivitas
            val newAddress = addressEditText.text.toString()
            addressUpdateListener.onAddressUpdated(newAddress) // Kirim alamat ke MainActivity
            dismiss() // Tutup dialog setelah melakukan perubahan alamat
        }

        // Setel click listener untuk tombol "Batal"
        cancelButton.setOnClickListener {
            dismiss() // Tutup dialog jika pengguna membatalkan perubahan alamat
        }

        // Bangun dialog menggunakan AlertDialog.Builder
        return AlertDialog.Builder(requireContext())
            .setTitle("Perbarui Alamat")
            .setView(view)
            .create()
    }
}