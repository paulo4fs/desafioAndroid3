package com.paulo.myapplication.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.model.ComicModel
import com.paulo.myapplication.data.repository.ComicsRepository
import kotlinx.coroutines.Dispatchers

class ComicsViewModel(
    private val repository: ComicsRepository
) : ViewModel() {
    private var _comics = listOf<ComicModel>()

    fun obterLista(
        ts: String,
        apikey: String,
        hash: String
    ) = liveData(Dispatchers.IO) {
        if (_comics.isEmpty()) {
            val response = repository
                .obterLista(ts, apikey, hash)
            _comics = response.data.results.toMutableList()

            imageFix()
        }

        emit(_comics)
    }

    private fun imageFix() {
        for (i in _comics.indices) {
            _comics[i].thumbnail.path = _comics[i].thumbnail.path.replace(
                "http://",
                "https://"
            )

            if (_comics[i].images.isNotEmpty()) {
                for (j in _comics[i].images.indices) {
                    _comics[i].images[j].path = _comics[i].images[j].path.replace(
                        "http://",
                        "https://"
                    )
                }
            }
        }
    }
}