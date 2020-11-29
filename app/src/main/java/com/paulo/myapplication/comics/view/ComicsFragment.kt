package com.paulo.myapplication.comics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            val bundle = bundleOf(
                "id" to it.id,
                "thumbnailExtension" to it.thumbnail.extension,
                "thumbnailPath" to it.thumbnail.path
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

        _comicsViewModel.obterLista(ts, apikey, hash)
            .observe(viewLifecycleOwner, {
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
