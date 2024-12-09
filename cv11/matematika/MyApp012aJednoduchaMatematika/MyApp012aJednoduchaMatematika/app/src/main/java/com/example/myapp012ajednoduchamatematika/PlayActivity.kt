package com.example.myapp012ajednoduchamatematika

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp012ajednoduchamatematika.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializace View Bindingu
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addition.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "+")
            startActivity(calInt)
        }

        binding.sub.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "-")
            startActivity(calInt)
        }

        binding.multi.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "*")
            startActivity(calInt)
        }

        binding.division.setOnClickListener {
            val calInt = Intent(this@PlayActivity, MainActivity::class.java)
            calInt.putExtra("cals", "/")
            startActivity(calInt)
        }


    }
}