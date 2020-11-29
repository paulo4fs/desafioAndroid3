package com.paulo.myapplication.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.repository.ComicRepository
import com.paulo.myapplication.data.model.ComicModel
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val repository: ComicRepository
) : ViewModel() {
    lateinit var comic: ComicModel

    fun obterItem(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ) = liveData(Dispatchers.IO) {
        val response = repository.obterItem(
            id, ts, apikey, hash
        )

        comic = response.data.results[0]

        emit(comic)
    }
}