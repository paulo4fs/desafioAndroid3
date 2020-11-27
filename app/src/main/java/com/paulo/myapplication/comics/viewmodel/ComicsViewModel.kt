package com.paulo.myapplication.comics.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.comics.model.ComicModel
import com.paulo.myapplication.comics.model.DataModel
import com.paulo.myapplication.comics.repository.ComicsRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Query

class ComicsViewModel(
    private val repository: ComicsRepository
) : ViewModel() {
    private lateinit var comics: List<ComicModel>

    fun obterLista(
        ts: String,
        apikey: String,
        hash: String,
        titleStarsWith: String
    ) =
        liveData(Dispatchers.IO) {
            val response = repository.obterLista(ts, apikey, hash, titleStarsWith)

            comics = response.data.results

            emit(comics)
        }
}