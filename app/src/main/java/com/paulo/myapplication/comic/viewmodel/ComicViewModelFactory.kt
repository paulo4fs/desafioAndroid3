package com.paulo.myapplication.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulo.myapplication.data.repository.ComicRepository

class ComicViewModelFactory(
    private val repository: ComicRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ComicViewModel(repository) as T
    }
}