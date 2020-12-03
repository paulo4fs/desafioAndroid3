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
    private lateinit var _comic: ComicModel

    fun getItem(id: Int) = liveData(Dispatchers.IO) {
        val response = repository.getComic(id)

        _comic = response.data.results[0]

        imageFix()
        dateFix()
        descriptionFix()

        emit(_comic)
    }


    private fun imageFix() {
        DataUtils.thumbnailFix(_comic)
        DataUtils.imageFix(_comic)
    }

    private fun dateFix() {
        DataUtils.dateFix(_comic)
    }

    private fun descriptionFix() {
        DataUtils.descriptionFix(_comic)
    }
}