package com.paulo.myapplication.comics.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class ComicsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val _imageView = view.findViewById<ImageView>(R.id.ivImageItem)
    private val _issueView = view.findViewById<TextView>(R.id.tvIssueNumberItem)

    fun bind(thumbnail: String, issue: Double) {
        _issueView.text = if (issue.rem(1) == 0.0) {
            "#${issue.toString().split('.')[0]}"
        } else {
            "#${issue}"
        }

        Picasso.get()
            .load(thumbnail)
            .fit()
            .centerCrop()
            .error(R.drawable.noimage)
            .into(_imageView)
    }
}