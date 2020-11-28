package com.paulo.myapplication.comic.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class CoverFragment : DialogFragment() {
    private lateinit var _view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        closeBtn()

        val image = arguments?.getInt("image")
        val imageView = view.findViewById<ImageView>(R.id.ivImageCover)

        if (image != null) {
            Picasso.get().load(image).into(imageView)
        }
    }

    private fun closeBtn() {
        val close = _view.findViewById<ImageButton>(R.id.ibCloseCover)

        close.setOnClickListener {
            var navcontroller = findNavController()
            navcontroller.navigate(R.id.action_coverFragment_to_comicFragment)
        }
    }
}