package com.paulo.myapplication.comics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.paulo.myapplication.data.model.ComicModel

class ComicsAdapter(
    private val comics: List<ComicModel>,
    private val listener: (ComicModel) -> Unit
) : RecyclerView.Adapter<ComicsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.comics_grid_item, parent, false)

        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val item = comics[position]

        val portraitMedium = "/portrait_medium."

        val issue = item.issueNumber

        val thumbnailJoin = item.thumbnail.path + portraitMedium + item.thumbnail.extension

        holder.bind(thumbnailJoin.replace("http", "https"), issue)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = comics.size
}