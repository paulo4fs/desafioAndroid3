package com.paulo.myapplication.comics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulo.myapplication.data.repository.ComicsRepository

class ComicsViewModelFactory(
    private val repository: ComicsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ComicsViewModel(repository) as T
    }
}