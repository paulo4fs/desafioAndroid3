package com.paulo.myapplication.cover.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.paulo.myapplication.R
import com.paulo.myapplication.data.utils.DataUtils
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

        closeBtn()

        val imagePath = arguments?.getString("imagePath")
        val imageExtension = arguments?.getString("imageExtension")

        val fullThumbnailImage =
            DataUtils.imageJoin(path = imagePath!!, extension = imageExtension!!)


        val imageView = view.findViewById<ImageView>(R.id.ivImageCover)

        Picasso.get()
            .load(fullThumbnailImage)
            .fit()
            .centerCrop()
            .error(R.drawable.noimage)
            .into(imageView)
    }

    private fun closeBtn() {
        val close = _view.findViewById<ImageButton>(R.id.ibCloseCover)

        close.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}