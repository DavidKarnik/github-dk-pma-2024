package com.example.myapp016avanocniappka

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TileAdapter(private val context: Context, private val items: List<String>) :
    RecyclerView.Adapter<TileAdapter.TileViewHolder>() {

    // Vytvoření ViewHolderu pro každou položku (dlaždici)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        return TileViewHolder(view)
    }

    // Naplnění ViewHolderu daty
    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        val item = items[position]
        holder.tileText.text = item

        // Nastavení onClickListeneru na celou CardView (dlaždici)
        holder.itemView.setOnClickListener {
            val intent = when (item) {
                "Pexeso" -> Intent(context, ImageGameActivity::class.java)
                "Kvíz" -> Intent(context, QuizGameActivity::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }
        }
    }

    // Počet položek v seznamu
    override fun getItemCount(): Int {
        return items.size
    }

    // ViewHolder pro každou položku (dlaždici)
    class TileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tileText: TextView = itemView.findViewById(R.id.tileText)
    }
}
