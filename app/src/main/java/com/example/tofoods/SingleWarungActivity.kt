package com.example.tofoods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tofoods.Adapter.SinglePageMakananAdapter
import com.example.tofoods.Adapter.SinglePageMinumanAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class SingleWarungActivity : AppCompatActivity() {

    private lateinit var rvDetailMakanan: RecyclerView
    private lateinit var rvDetailMinuman: RecyclerView
    private lateinit var clickableMakananMenu: CardView
    private lateinit var bottomSheetPesanan: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_warung)

        rvDetailMakanan = findViewById(R.id.makanan_list)
        rvDetailMinuman = findViewById(R.id.minuman_list)




        makananView()
        minumanView()
    }

    private fun makananView() {
        rvDetailMakanan.layoutManager = GridLayoutManager(this, 2)
        rvDetailMakanan.adapter = SinglePageMakananAdapter()
    }

    private fun minumanView() {
        rvDetailMinuman.layoutManager = LinearLayoutManager(this)
        rvDetailMinuman.adapter = SinglePageMinumanAdapter()
    }
}