package com.paulo.myapplication.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.utils.DataUtils
import com.paulo.myapplication.data.repository.ComicRepository
import com.paulo.myapplication.data.model.ComicModel
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val repository: ComicRepository
) : ViewModel() {
    lateinit var comic: ComicModel

    fun obterItem(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.obterItem(id)

        comic = response.data.results[0]

        imageFix()
        dateFix()
        descriptionFix()

        emit(comic)
    }


    private fun imageFix() {
        DataUtils.thumbnailFix(comic)
        DataUtils.imageFix(comic)
    }

    private fun dateFix() {
        DataUtils.dateFix(comic)
    }

    private fun descriptionFix() {
        DataUtils.descriptionFix(comic)
    }
}