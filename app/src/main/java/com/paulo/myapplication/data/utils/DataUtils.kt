package com.paulo.myapplication.data.utils

import com.paulo.myapplication.data.model.ComicModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DataUtils {
    fun thumbnailFix(comic: ComicModel): ComicModel {
        comic.thumbnail.path = comic.thumbnail.path.replace(
            "http://",
            "https://"
        )
        return comic
    }

    fun imageFix(comic: ComicModel): ComicModel {
        if (comic.images.isNotEmpty()) {
            for (j in comic.images.indices) {
                comic.images[j].path = comic.images[j].path.replace(
                    "http://",
                    "https://"
                )
            }
        }
        return comic
    }

    fun dateFix(comic: ComicModel): ComicModel {
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
        return comic
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
