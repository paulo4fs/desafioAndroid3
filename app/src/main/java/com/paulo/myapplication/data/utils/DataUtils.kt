package com.paulo.myapplication.data.utils

import android.os.Build
import com.paulo.myapplication.data.model.ComicModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DataUtils {
    fun thumbnailFix(comic: ComicModel): ComicModel {
        comic.thumbnail.path = comic.thumbnail.path.replace(
            Constants.HTTP,
            Constants.HTTPS
        )
        return comic
    }

    fun imageFix(comic: ComicModel): ComicModel {
        if (comic.images.isNotEmpty()) {
            for (j in comic.images.indices) {
                comic.images[j].path = comic.images[j].path.replace(
                    Constants.HTTP,
                    Constants.HTTPS
                )
            }
        }
        return comic
    }

    fun dateFix(date: String): String {
        return if (date.startsWith('-')) {
            "date undefined"
        } else {
            val dateString = date.split('T')[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val parsedDate =
                    LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val formattedDate =
                    parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                formattedDate
            } else {
                ""
            }
        }
    }

    fun descriptionFix(comic: ComicModel): ComicModel {
        if (comic.description.isNullOrEmpty()) {
            comic.description = "No description."
        }
        return comic
    }

    fun imageJoin(path: String, format: String = ".", extension: String): String {
        return path + format + extension
    }
}
