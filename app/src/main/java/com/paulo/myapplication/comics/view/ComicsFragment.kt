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
            val bundle = bundleOf(
                "id" to it.id,
                "thumbnailExtension" to it.thumbnail.extension,
                "thumbnailPath" to it.thumbnail.path
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

        _comicsViewModel.getComics()
            .observe(viewLifecycleOwner, {
                showList(it)
            })
    }

    private fun showList(lista: List<ComicModel>) {
        lista.let {
            _comicsList.addAll(it)
            _comicsAdapter.notifyDataSetChanged()
        }
    }
}
