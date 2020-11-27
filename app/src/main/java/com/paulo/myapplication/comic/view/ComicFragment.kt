package com.paulo.myapplication.comic.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.paulo.myapplication.R
import com.paulo.myapplication.comics.model.ComicModel
import com.paulo.myapplication.comics.repository.ComicsRepository
import com.paulo.myapplication.comics.viewmodel.ComicsViewModel
import com.paulo.myapplication.comics.viewmodel.ComicsViewModelFactory
import com.squareup.picasso.Picasso

class ComicFragment : Fragment() {
    private lateinit var _view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        setData()
        backBtn()
    }

    private fun setData() {
        val imagePath = arguments?.getString("imagePath")
        val imageExtension = arguments?.getString("imageExtension")
        val thumbnailPath = arguments?.getString("thumbnailPath")
        val thumbnailExtension = arguments?.getString("thumbnailExtension")
        val price = arguments?.getInt("price")
        val description = arguments?.getString("description")
        val title = arguments?.getString("title")
        val pageCount = arguments?.getInt("pageCount")

        val image = imagePath + getString(R.string.landscape_incredible) + imageExtension
        val thumbnail = thumbnailPath + getString(R.string.portrait_incredible) + thumbnailExtension

        val thumbnailImage = _view.findViewById<ImageView>(R.id.ivThumbnailComic)
        val coverImage = _view.findViewById<ImageView>(R.id.ivImageComic)
        val priceText = _view.findViewById<TextView>(R.id.tvPriceComic)
        val descriptionText = _view.findViewById<TextView>(R.id.tvDescriptionComic)
        val titleText = _view.findViewById<TextView>(R.id.tvTitleComic)
        val pageCountText = _view.findViewById<TextView>(R.id.tvPagecountComic)

        priceText.text = "$ " + price.toString()
        descriptionText.text = description
        titleText.text = title
        pageCountText.text = pageCount.toString()
        Picasso.get().load(image).into(coverImage)
        Picasso.get().load(thumbnail).into(thumbnailImage)
    }

    private fun backBtn() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackbuttonComic)
        backBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_comicFragment_to_comicsFragment)
        }
    }
}