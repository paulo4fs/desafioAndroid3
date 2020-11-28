package com.paulo.myapplication.comics.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.comics.model.ComicModel
import com.paulo.myapplication.comics.repository.ComicsRepository
import kotlinx.coroutines.Dispatchers

class ComicsViewModel(
    private val repository: ComicsRepository
) : ViewModel() {
    private lateinit var _comics: List<ComicModel>

    fun obterLista(
        ts: String,
        apikey: String,
        hash: String,
        titleStarsWith: String
    ) = liveData(Dispatchers.IO) {
        val response = repository
            .obterLista(
                ts,
                apikey,
                hash,
                titleStarsWith
            )

        _comics = response.data.results

        emit(_comics)
    }
}