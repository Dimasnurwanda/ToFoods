package com.example.tofoods.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tofoods.R
import com.example.tofoods.pemesananActivity

class SinglePageMinumanAdapter:  RecyclerView.Adapter<SinglePageMinumanAdapter.SingleMinumanViewHolder>() {
    class SingleMinumanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnMinuman = itemView.findViewById<View>(R.id.ll_detail_minuman)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMinumanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_minuman_item, parent, false)
        return SingleMinumanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: SingleMinumanViewHolder, position: Int) {
        holder.btnMinuman.setOnClickListener {
            // buatkan view untuk bottom sheet dialog
            val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.sheet_pemesanan, null)
            // buatkan bottom sheet dialog
            val bottomSheetDialog = com.google.android.material.bottomsheet.BottomSheetDialog(holder.itemView.context)
            // set view untuk bottom sheet dialog
            bottomSheetDialog.setContentView(view)
            // tampilkan bottom sheet dialog
            bottomSheetDialog.show()
            // Dapatkan tombol pada layout sheet_pemesanan
            val btnPindahHalaman = view.findViewById<Button>(R.id.orderButton)

            // Set fungsi onClick untuk tombol btnPindahHalaman
            btnPindahHalaman.setOnClickListener {
                // Buat Intent untuk berpindah ke HalamanTujuanActivity
                val intent = Intent(holder.itemView.context, pemesananActivity::class.java)
                Toast.makeText(holder.itemView.context, "Tombol Pesan diklik!", Toast.LENGTH_SHORT).show()

                // Jalankan intent untuk memulai HalamanTujuanActivity
                holder.itemView.context.startActivity(intent)

                // Tutup bottom sheet dialog setelah berpindah halaman (opsional)
                bottomSheetDialog.dismiss()
            }

            // tampilkan bottom sheet dialog
            bottomSheetDialog.show()
        }
    }
}