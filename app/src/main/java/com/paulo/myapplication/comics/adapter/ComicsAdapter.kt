package com.paulo.myapplication.comics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.paulo.myapplication.data.model.ComicModel
import com.paulo.myapplication.data.utils.DataUtils

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

        val issueNumber = item.issueNumber

        val fullThumbnail =
            DataUtils.imageJoin(
                item.thumbnail.path,
                portraitMedium,
                item.thumbnail.extension
            )

        holder.bind(fullThumbnail, issueNumber)

        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = comics.size
}