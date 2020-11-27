package com.paulo.myapplication.comics.model

data class ComicModel(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String?,
    val pageCount: Int,
    val dates: List<DateModel>,
    val prices: List<PriceModel>,
    val thumbnail: ThumbnailModel,
    val images:List<ImageModel>
)