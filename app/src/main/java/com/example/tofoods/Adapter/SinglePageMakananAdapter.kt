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
import com.google.android.material.bottomsheet.BottomSheetDialog

class SinglePageMakananAdapter: RecyclerView.Adapter<SinglePageMakananAdapter.SingleMakananViewHolder>() {
    class SingleMakananViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnMakanan = itemView.findViewById<View>(R.id.cv_detail_makanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMakananViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_makanan_item, parent, false)
        return SingleMakananViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: SingleMakananViewHolder, position: Int) {
        holder.btnMakanan.setOnClickListener {
            // buatkan view untuk bottom sheet dialog
            val view = LayoutInflater.from(holder.itemView.context).inflate(R.layout.sheet_pemesanan, null)
            // buatkan bottom sheet dialog
            val bottomSheetDialog = BottomSheetDialog(holder.itemView.context)
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