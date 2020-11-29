package com.paulo.myapplication.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.repository.ComicRepository
import com.paulo.myapplication.data.model.ComicModel
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val repository: ComicRepository
) : ViewModel() {
    private lateinit var _comic: ComicModel

    /*  private lateinit var _image: String
      private lateinit var _thumbnail: String
      private lateinit var _price: String
      private lateinit var _description: String
      private lateinit var _title: String
      private lateinit var _date: String
      private lateinit var _pagecount: String*/

    fun obterItem(
        id: Int,
        ts: String,
        apikey: String,
        hash: String
    ) = liveData(Dispatchers.IO) {
        val response = repository.obterItem(
            id, ts, apikey, hash
        )

        _comic = response.data.results[0]

        emit(_comic)
    }
}