package com.paulo.myapplication.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.model.ComicModel
import com.paulo.myapplication.data.repository.ComicsRepository
import com.paulo.myapplication.data.utils.DataUtils
import kotlinx.coroutines.Dispatchers

class ComicsViewModel(
    private val repository: ComicsRepository
) : ViewModel() {
    private var _comics = listOf<ComicModel>()

    fun obterComics() = liveData(Dispatchers.IO) {
        if (_comics.isEmpty()) {
            val response = repository
                .obterComics()
            _comics = response.data.results.toMutableList()

            imageFix()
        }

        emit(_comics)
    }

    private fun imageFix() {
        for (i in _comics.indices) {
            DataUtils.thumbnailFix(_comics[i])
            if (_comics[i].images.isNotEmpty()) {
                DataUtils.imageFix(_comics[i])
            }
        }
    }
}