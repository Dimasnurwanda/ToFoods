package com.example.tofoods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tofoods.Fragment.HomeFragment
import com.example.tofoods.Fragment.PesananFragment
import com.example.tofoods.Fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCategory: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val pesananFragment = PesananFragment()
        val profileFragment = ProfileFragment()

        val navbar = findViewById<BottomNavigationView>(R.id.navbar)

        makeCurrentFragment(homeFragment)

        navbar.setOnNavigationItemReselectedListener {
            when(it.itemId) {
                R.id.nav_home -> makeCurrentFragment(homeFragment)
                R.id.nav_pesanan -> makeCurrentFragment(pesananFragment)
                R.id.nav_profile -> makeCurrentFragment(profileFragment)
            }
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_navbar, fragment)
            commit()
        }
    }
}