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
import com.paulo.myapplication.comic.repository.ComicRepository
import com.paulo.myapplication.comic.viewmodel.ComicViewModel
import com.paulo.myapplication.comic.viewmodel.ComicViewModelFactory
import com.paulo.myapplication.comics.model.ComicModel

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
        thumbnailBtn(idArgument)
    }

    private fun exibirItem(it: ComicModel) {
        val thumbnailImage = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)
        val coverImage = _view.findViewById<ImageView>(R.id.ivImageComic)
        val priceText = _view.findViewById<TextView>(R.id.tvPriceComic)
        val descriptionText = _view.findViewById<TextView>(R.id.tvDescriptionComic)
        val titleText = _view.findViewById<TextView>(R.id.tvTitleComic)
        val pageCountText = _view.findViewById<TextView>(R.id.tvPagecountComic)
        val dateText = _view.findViewById<TextView>(R.id.tvDateComic)

        priceText.text = "$ ${it.prices[0].price}"
        descriptionText.text = it.description
        titleText.text = it.title
        pageCountText.text = it.pageCount.toString()
        dateText.text = it.dates[0].date.split('T')[0]
    }

    /*private fun setData() {
        val imagePath = arguments?.getString("imagePath")
        val imageExtension = arguments?.getString("imageExtension")
        val thumbnailPath = arguments?.getString("thumbnailPath")
        val thumbnailExtension = arguments?.getString("thumbnailExtension")
        val price = arguments?.getFloat("price")
        val description = arguments?.getString("description")
        val title = arguments?.getString("title")
        val pageCount = arguments?.getInt("pageCount")
        val date = arguments?.getString("date")

        val image = imagePath + "/portrait_medium." + imageExtension
        val thumbnail = thumbnailPath + getString(R.string.portrait_incredible) + thumbnailExtension

        val thumbnailImage = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)
        val coverImage = _view.findViewById<ImageView>(R.id.ivImageComic)
        val priceText = _view.findViewById<TextView>(R.id.tvPriceComic)
        val descriptionText = _view.findViewById<TextView>(R.id.tvDescriptionComic)
        val titleText = _view.findViewById<TextView>(R.id.tvTitleComic)
        val pageCountText = _view.findViewById<TextView>(R.id.tvPagecountComic)
        val dateText = _view.findViewById<TextView>(R.id.tvDateComic)

        priceText.text = "$ ${price}"
        descriptionText.text = description
        titleText.text = title
        pageCountText.text = pageCount.toString()
        dateText.text = date

        Log.d("imagem", image)

        Picasso.get()
            .load(R.drawable.landscape_incredible)
            .into(coverImage)

        Picasso.get()
            .load("http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_xlarge.jpg")
            .into(thumbnailImage)
    }*/

    private fun backBtn() {
        val backBtn = _view.findViewById<ImageButton>(R.id.ibBackbuttonComic)
        backBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_comicFragment_to_comicsFragment)
        }
    }

    private fun thumbnailBtn(id: Int) {
        val thumbnailBtn = _view.findViewById<ImageButton>(R.id.ibThumbnailComic)

        val bundle = bundleOf("image" to R.drawable.portrait_incredible, "id" to id)

        thumbnailBtn.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_comicFragment_to_coverFragment, bundle)
        }
    }
}