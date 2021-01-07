package com.paulo.myapplication.comics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.paulo.myapplication.comics.adapter.ComicsAdapter
import com.paulo.myapplication.comics.viewmodel.ComicsViewModel
import com.paulo.myapplication.comics.viewmodel.ComicsViewModelFactory
import com.paulo.myapplication.data.model.ComicModel
import com.paulo.myapplication.data.repository.ComicsRepository
import com.paulo.myapplication.data.utils.Constants.DATE
import com.paulo.myapplication.data.utils.Constants.DESCRIPTION
import com.paulo.myapplication.data.utils.Constants.ID
import com.paulo.myapplication.data.utils.Constants.IMAGE_EXTENSION
import com.paulo.myapplication.data.utils.Constants.IMAGE_PATH
import com.paulo.myapplication.data.utils.Constants.PAGE_COUNT
import com.paulo.myapplication.data.utils.Constants.PRICE
import com.paulo.myapplication.data.utils.Constants.THUMBNAIL_EXTENSION
import com.paulo.myapplication.data.utils.Constants.THUMBNAIL_PATH
import com.paulo.myapplication.data.utils.Constants.TITLE

class
ComicsFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _comicsViewModel: ComicsViewModel
    private lateinit var _list: RecyclerView

    private lateinit var _comicsAdapter: ComicsAdapter

    private var _comicsList = mutableListOf<ComicModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        _list = view.findViewById(R.id.recyclerview)

        val manager = GridLayoutManager(view.context, 3)

        _comicsList = mutableListOf()
        _comicsAdapter = ComicsAdapter(_comicsList) {

            var imagePath: String? = null
            var imageExtension: String? = null
            if (it.images.isNotEmpty()) {
                val size = it.images.size
                imagePath = it.images[size - 1].path
                imageExtension = it.images[size - 1].extension
            }

            val bundle = bundleOf(
                ID to it.id,
                THUMBNAIL_EXTENSION to it.thumbnail.extension,
                THUMBNAIL_PATH to it.thumbnail.path,
                IMAGE_PATH to imagePath,
                IMAGE_EXTENSION to imageExtension,
                PRICE to it.prices[0].price,
                DESCRIPTION to it.description,
                TITLE to it.title,
                PAGE_COUNT to it.pageCount.toString(),
                DATE to it.dates[1].date
            )
            val navController = findNavController()
            navController.navigate(R.id.action_comicsFragment_to_comicFragment, bundle)
        }

        _list.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _comicsAdapter
        }

        _comicsViewModel = ViewModelProvider(
            this,
            ComicsViewModelFactory(ComicsRepository())
        ).get(ComicsViewModel::class.java)

        _comicsViewModel.getComics().observe(viewLifecycleOwner, {
            showList(it)
        })

        showLoading(true)
    }

    private fun showList(lista: List<ComicModel>) {
        showLoading(false)

        lista.let {
            _comicsList.addAll(it)
        }
        _comicsAdapter.notifyDataSetChanged()
    }

    private fun showLoading(isLoading: Boolean) {
        val progressBar = _view.findViewById<ProgressBar>(R.id.pbLoading)

        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
