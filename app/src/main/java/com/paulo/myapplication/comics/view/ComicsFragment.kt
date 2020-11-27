package com.paulo.myapplication.comics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paulo.myapplication.R
import com.paulo.myapplication.comics.adapter.ComicsAdapter
import com.paulo.myapplication.comics.model.ComicModel
import com.paulo.myapplication.comics.repository.ComicsRepository
import com.paulo.myapplication.comics.viewmodel.ComicsViewModel
import com.paulo.myapplication.comics.viewmodel.ComicsViewModelFactory

class ComicsFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _comicsViewModel: ComicsViewModel
    private lateinit var _lista: RecyclerView

    private lateinit var _comicsAdapter: ComicsAdapter

    private var _listaDeComics = mutableListOf<ComicModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        _lista = view.findViewById(R.id.recyclerview)

        val manager = GridLayoutManager(view.context, 3)

        _listaDeComics = mutableListOf()
        _comicsAdapter = ComicsAdapter(_listaDeComics) {
            Toast.makeText(view.context, "Issue: ${it.issueNumber}", Toast.LENGTH_SHORT).show()
            _comicsViewModel.setComic(it)

            val bundle = bundleOf(
                "imagePath" to it.images[0].path,
                "imageExtension" to it.images[0].extension,
                "thumbnailPath" to it.thumbnail.path,
                "thumbnailExtension" to it.thumbnail.extension,
                "price" to it.prices[0].price,
                "description" to it.description,
                "date" to it.dates[0].date.time,
                "title" to it.title,
                "pageCount" to it.pageCount
            )

            val navController = findNavController()
            navController.navigate(R.id.action_comicsFragment_to_comicFragment, bundle)
        }

        _lista.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = _comicsAdapter
        }

        _comicsViewModel = ViewModelProvider(
            this,
            ComicsViewModelFactory(ComicsRepository())
        ).get(ComicsViewModel::class.java)

        val ts = "1606439381833"
        val apikey = "d5eb389c4ed264949086922b7b0c3545"
        val hash = "06a90a70b6362cdb5021e65d2b183dcc"
        val titleStartsWith = "spider"

        _comicsViewModel.obterLista(ts, apikey, hash, titleStartsWith).observe(viewLifecycleOwner, {
            exibirLista(it)
        })
    }

    private fun exibirLista(lista: List<ComicModel>) {
        lista.let {
            _listaDeComics.addAll(it)
            _comicsAdapter.notifyDataSetChanged()
        }
    }
}