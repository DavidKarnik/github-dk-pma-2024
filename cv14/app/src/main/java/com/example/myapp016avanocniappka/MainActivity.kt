package com.example.myapp016avanocniappka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Vytvoření seznamu názvů her
        val games = listOf("Pexeso", "Kvíz")

        // Nastavení LayoutManageru pro mřížku
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 sloupce

        // Nastavení adapteru pro RecyclerView
        recyclerView.adapter = TileAdapter(this, games)
    }
}
