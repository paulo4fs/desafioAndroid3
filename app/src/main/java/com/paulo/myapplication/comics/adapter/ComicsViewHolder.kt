package com.paulo.myapplication.comics.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class ComicsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val _imageView = view.findViewById<ImageView>(R.id.ivImageItem)
    private val _issueView = view.findViewById<TextView>(R.id.tvIssuenumberItem)

    fun bind(imagem: String, issue: Double) {
        if (issue.rem(1) == 0.0) {
            _issueView.text = "#${issue.toString().split('.')[0]}"
        } else {
            _issueView.text = "#${issue}"
        }

        Log.d("teste", imagem)

        Picasso.get()
            .load(imagem)
            .into(_imageView)
    }
}