package com.idmdragon.movieplay.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.idmdragon.movieplay.R
import com.idmdragon.movieplay.constant.ConstantPage.PAGE_SEARCH_ACTIVITY
import com.idmdragon.movieplay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        binding.ibSearch.setOnClickListener {
            startActivity(Intent(this, Class.forName(PAGE_SEARCH_ACTIVITY)))
        }
    }

    override fun onBackPressed() {
        val t = System.currentTimeMillis()
        if (t - backPressedTime > 2000) {
            backPressedTime = t
            Toast.makeText(
                this, getString(R.string.text_press_one_again_to_exit),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            finishAffinity()
        }
    }
}