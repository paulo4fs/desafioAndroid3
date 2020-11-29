package com.paulo.myapplication.comic.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class CoverFragment : DialogFragment() {
    private lateinit var _view: View

    override fun onStart() {
        super.onStart()

        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
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


        val image = arguments?.getInt("image")
        val id = arguments?.getInt("id")!!
        val imageView = view.findViewById<ImageView>(R.id.ivImageCover)

        closeBtn(id)

        if (image != null) {
            Picasso.get().load(image).into(imageView)
        }
    }

    private fun closeBtn(id: Int) {
        val close = _view.findViewById<ImageButton>(R.id.ibCloseCover)

        var bundle = bundleOf("id" to id)

        close.setOnClickListener {
            var navcontroller = findNavController()
            navcontroller.navigate(R.id.action_coverFragment_to_comicFragment, bundle)
        }
    }
}