package com.paulo.myapplication.comics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.paulo.myapplication.comics.model.ComicModel

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

        if (item.images.isNotEmpty()) {
            val imageJoin =
                item.images[0].path + portraitMedium + item.images[0].extension


            holder.bind(imageJoin, issue)
        } else {
            holder.bind("", issue)
        }


        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = comics.size
}