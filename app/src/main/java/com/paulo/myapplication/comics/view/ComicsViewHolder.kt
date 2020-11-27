package com.paulo.myapplication.comics.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class ComicsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val imagemView = view.findViewById<ImageView>(R.id.ivImageItem)
    private val issueView = view.findViewById<TextView>(R.id.tvIssuenumberItem)

    fun bind(imagem: String, issue: Double) {
        issueView.text = issue.toString()
        Log.d("teste", imagem)
        Picasso.get().load(imagem).into(imagemView)
    }
}