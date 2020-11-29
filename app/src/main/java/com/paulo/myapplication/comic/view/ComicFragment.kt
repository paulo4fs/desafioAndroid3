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
import com.paulo.myapplication.data.model.ComicModel
import com.squareup.picasso.Picasso

class ComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _comicViewModel: ComicViewModel

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

        val idArgument = arguments?.getInt("id")!!
        val thumbnailExtensionArgument = arguments?.getString("thumbnailExtension")
        val thumbnailPathArgument = arguments?.getString("thumbnailPath")

        _comicViewModel = ViewModelProvider(
            this,
            ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        val ts = "1606439381833"
        val apikey = "d5eb389c4ed264949086922b7b0c3545"
        val hash = "06a90a70b6362cdb5021e65d2b183dcc"


        _comicViewModel.obterItem(idArgument, ts, apikey, hash).observe(viewLifecycleOwner, {
            exibirItem(it)
        })

        backBtn()
        thumbnailBtn(idArgument, thumbnailPathArgument!!, thumbnailExtensionArgument!!)
    }

    private fun exibirItem(comic: ComicModel) {
        val thumbnailImage = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)
        val coverImage = _view.findViewById<ImageView>(R.id.ivImageComic)
        val priceText = _view.findViewById<TextView>(R.id.tvPriceComic)
        val descriptionText = _view.findViewById<TextView>(R.id.tvDescriptionComic)
        val titleText = _view.findViewById<TextView>(R.id.tvTitleComic)
        val pageCountText = _view.findViewById<TextView>(R.id.tvPagecountComic)
        val dateText = _view.findViewById<TextView>(R.id.tvDateComic)

        priceText.text = "$ ${comic.prices[0].price}"
        descriptionText.text = comic.description
        titleText.text = comic.title
        pageCountText.text = comic.pageCount.toString()
        dateText.text = comic.dates[0].date.split('T')[0]

        if (comic.images.isNotEmpty()) {
            val lastImage = comic.images.size - 1
            val imageUrl =
                (comic.images[lastImage].path + getString(R.string.landscape_incredible) + comic.images[lastImage].extension)

            Picasso.get()
                .load(imageUrl)
                .error(R.drawable.noimage)
                .into(coverImage)
        } else {
            Picasso.get()
                .load(R.drawable.noimage)
                .into(coverImage)
        }

        val thumbnail =
            comic.thumbnail.path + getString(R.string.portrait_incredible) + comic.thumbnail.extension

        Picasso.get()
            .load(thumbnail)
            .error(R.drawable.noimage)
            .into(thumbnailImage)
    }

    private fun backBtn() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackbuttonComic)
        backBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_comicFragment_to_comicsFragment)
        }
    }

    private fun thumbnailBtn(id: Int, thumbnailPath: String, thumbnailExtension: String) {
        val thumbnailBtn = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)

        val bundle = bundleOf(
            "id" to id,
            "thumbnailPath" to thumbnailPath,
            "thumbnailExtension" to thumbnailExtension
        )

        thumbnailBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_comicFragment_to_coverFragment, bundle)
        }
    }
}