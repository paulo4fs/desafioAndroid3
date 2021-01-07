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

}