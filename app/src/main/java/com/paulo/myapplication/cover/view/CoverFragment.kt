package com.paulo.myapplication.cover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.paulo.myapplication.R
import com.squareup.picasso.Picasso

class CoverFragment : Fragment() {
    private lateinit var _view: View


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

        val image = arguments?.getString("image")!!.replace("http", "https")
        val id = arguments?.getInt("id")!!
        val imageView = view.findViewById<ImageView>(R.id.ivImageCover)

        closeBtn(id)

        Picasso.get().load(image).into(imageView)
    }

    private fun closeBtn(id: Int) {
        val close = _view.findViewById<ImageButton>(R.id.ibCloseCover)

        val bundle = bundleOf("id" to id)

        close.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_coverFragment_to_comicFragment, bundle)
        }
    }
}