package com.paulo.myapplication.comic.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.paulo.myapplication.R
import com.paulo.myapplication.data.repository.ComicRepository
import com.paulo.myapplication.comic.viewmodel.ComicViewModel
import com.paulo.myapplication.comic.viewmodel.ComicViewModelFactory
import com.paulo.myapplication.data.utils.Constants.DATE
import com.paulo.myapplication.data.utils.Constants.DESCRIPTION
import com.paulo.myapplication.data.utils.Constants.ID
import com.paulo.myapplication.data.utils.Constants.IMAGE_EXTENSION
import com.paulo.myapplication.data.utils.Constants.IMAGE_PATH
import com.paulo.myapplication.data.utils.Constants.LANDSCAPE_INCREDIBLE
import com.paulo.myapplication.data.utils.Constants.PAGE_COUNT
import com.paulo.myapplication.data.utils.Constants.PORTRAIT_INCREDIBLE
import com.paulo.myapplication.data.utils.Constants.PRICE
import com.paulo.myapplication.data.utils.Constants.THUMBNAIL_EXTENSION
import com.paulo.myapplication.data.utils.Constants.THUMBNAIL_PATH
import com.paulo.myapplication.data.utils.Constants.TITLE
import com.paulo.myapplication.data.utils.DataUtils
import com.squareup.picasso.Picasso

class ComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _comicViewModel: ComicViewModel

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

        val idArgument = arguments?.getInt(ID)!!
        val thumbnailExtensionArgument = arguments?.getString(THUMBNAIL_EXTENSION)!!
        val thumbnailPathArgument = arguments?.getString(THUMBNAIL_PATH)!!
        val imagePathArgument = arguments?.getString(IMAGE_PATH)
        val imageExtensionArgument = arguments?.getString(IMAGE_EXTENSION)
        val priceArgument = arguments?.getDouble(PRICE)!!
        val descriptionArgument = arguments?.getString(DESCRIPTION)
        val titleArgument = arguments?.getString(TITLE)!!
        val pageCountArgument = arguments?.getInt(PAGE_COUNT)!!
        val dateArgument = arguments?.getString(DATE)!!

        _comicViewModel = ViewModelProvider(
            this,
            ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        showItem(
            thumbnailExtensionArgument,
            thumbnailPathArgument,
            imagePathArgument,
            imageExtensionArgument,
            priceArgument,
            descriptionArgument,
            titleArgument,
            pageCountArgument,
            dateArgument
        )

        backBtn()
        thumbnailBtn(
            idArgument,
            thumbnailPathArgument,
            thumbnailExtensionArgument
        )
    }

    private fun showItem(
        thumbnailExtension: String,
        thumbnailPath: String,
        imagePath: String?,
        imageExtension: String?,
        price: Double,
        description: String?,
        title: String,
        pageCount: Int,
        date: String
    ) {
        val thumbnailImage = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)
        val coverImage = _view.findViewById<ImageView>(R.id.ivImageComic)
        val priceText = _view.findViewById<TextView>(R.id.tvPriceComic)
        val descriptionText = _view.findViewById<TextView>(R.id.tvDescriptionComic)
        val titleText = _view.findViewById<TextView>(R.id.tvTitleComic)
        val pageCountText = _view.findViewById<TextView>(R.id.tvPageCountComic)
        val dateText = _view.findViewById<TextView>(R.id.tvDateComic)

        ("$ $price").also { priceText.text = it }
        descriptionText.text = description
        titleText.text = title
        pageCountText.text = pageCount.toString()
        dateText.text = DataUtils.dateFix(date)

        if (!imagePath.isNullOrEmpty() && !imageExtension.isNullOrEmpty()) {
            val fullLandscapeImage =
                DataUtils.imageJoin(
                    imagePath,
                    LANDSCAPE_INCREDIBLE,
                    imageExtension
                )

            Picasso.get()
                .load(fullLandscapeImage)
                .fit()
                .centerCrop()
                .error(R.drawable.noimage)
                .into(coverImage)

            coverImage.setOnClickListener {
                imageLauncher(id, imagePath, imageExtension)
            }
        } else {
            Picasso.get()
                .load(R.drawable.noimage)
                .fit()
                .centerCrop()
                .into(coverImage)
        }

        val fullThumbnail = DataUtils.imageJoin(
            thumbnailPath,
            PORTRAIT_INCREDIBLE,
            thumbnailExtension
        )

        Picasso.get()
            .load(fullThumbnail)
            .fit()
            .centerCrop()
            .error(R.drawable.noimage)
            .into(thumbnailImage)
    }

    private fun backBtn() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackButtonComic)
        backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun thumbnailBtn(
        id: Int,
        thumbnailPath: String,
        thumbnailExtension: String,
    ) {
        val thumbnailBtn = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)

        thumbnailBtn.setOnClickListener {
            imageLauncher(id, thumbnailPath, thumbnailExtension)
        }
    }

    private fun imageLauncher(id: Int, imagePath: String, imageExtension: String) {
        val bundle = bundleOf(
            ID to id,
            IMAGE_PATH to imagePath,
            IMAGE_EXTENSION to imageExtension
        )

        val navController = findNavController()
        navController.navigate(R.id.action_comicFragment_to_coverFragment, bundle)
    }
}