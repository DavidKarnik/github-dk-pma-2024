package com.example.myapp016avanocniappka

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp016avanocniappka.R.drawable
import kotlin.random.Random

class ImageGameActivity : AppCompatActivity() {

    private lateinit var gameGrid: GridLayout
    private lateinit var resultText: TextView

    private var flippedTiles = mutableListOf<View>()
    private val pairs = mutableListOf<Int>()
    private val revealedTiles = mutableSetOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_game)

        gameGrid = findViewById(R.id.gameGrid)
        resultText = findViewById(R.id.resultText)

        setupGame()
    }

    private fun setupGame() {
        val tileImages = listOf(
            drawable.image1, drawable.image2, drawable.image3,
            drawable.image1, drawable.image2, drawable.image3,
            drawable.image4, drawable.image4, drawable.image5
        ).shuffled(Random(System.currentTimeMillis()))

        for (i in 0 until 9) {
            val tile = View(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                setBackgroundResource(R.drawable.tile_back)
                setOnClickListener { onTileClicked(this, tileImages[i]) }
            }
            gameGrid.addView(tile)
        }
    }

    private fun onTileClicked(tile: View, imageRes: Int) {
        checkSuccess()

        if (tile in revealedTiles || tile in flippedTiles) return

        tile.setBackgroundResource(imageRes)
        flippedTiles.add(tile)

        if (flippedTiles.size == 2) {
            Handler().postDelayed({
                checkMatch()
            }, 1000)
        }
    }

    private fun checkMatch() {
        val firstTile = flippedTiles[0]
        val secondTile = flippedTiles[1]

        if (firstTile.background.constantState == secondTile.background.constantState) {
            revealedTiles.add(firstTile)
            revealedTiles.add(secondTile)
            Toast.makeText(this, "Pár nalezen!", Toast.LENGTH_SHORT).show()
        } else {
            firstTile.setBackgroundResource(R.drawable.tile_back)
            secondTile.setBackgroundResource(R.drawable.tile_back)
        }

        flippedTiles.clear()
    }

    private fun checkSuccess() {
        if (revealedTiles.size >= 8) {
            resultText.visibility = View.VISIBLE
            Toast.makeText(this, "Vyhráli jste!", Toast.LENGTH_LONG).show()
        }
    }
}
