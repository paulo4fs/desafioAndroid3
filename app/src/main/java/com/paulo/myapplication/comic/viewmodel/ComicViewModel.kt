package com.paulo.myapplication.comic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.paulo.myapplication.data.repository.ComicRepository
import com.paulo.myapplication.data.model.ComicModel
import kotlinx.coroutines.Dispatchers
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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

        imageFix()
        dateFix()

        emit(comic)
    }


    private fun imageFix() {
        comic.thumbnail.path = comic.thumbnail.path.replace(
            "http://",
            "https://"
        )

        if (comic.images.isNotEmpty()) {
            for (j in comic.images.indices) {
                comic.images[j].path = comic.images[j].path.replace(
                    "http://",
                    "https://"
                )
            }
        }
    }

    private fun dateFix() {
        if (comic.dates[1].date.startsWith('-')) {
            comic.dates[1].date = "date undefined"
        } else {
            val dateString = comic.dates[1].date.split('T')[0]
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val parsedDate =
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val formattedDate =
                    parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                comic.dates[1].date = formattedDate
            }
        }
    }
}