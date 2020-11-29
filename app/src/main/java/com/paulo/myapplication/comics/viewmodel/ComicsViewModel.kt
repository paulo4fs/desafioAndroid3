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
    private var _comics: List<ComicModel> = listOf()

    //        characters: String
    fun obterLista(
        ts: String,
        apikey: String,
        hash: String
    ) = liveData(Dispatchers.IO) {
        if (_comics.isEmpty()) {
            val response = repository
                .obterLista(
                    ts,
                    apikey,
                    hash,
                )
//                    characters
            _comics = response.data.results
        }

        emit(_comics)
    }
}