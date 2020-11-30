package com.paulo.myapplication.data.model

data class ComicModel(
    val id: Int,
    val title: String,
    val issueNumber: Double,
    var description: String?,
    val pageCount: Int,
    val dates: List<DateModel>,
    val prices: List<PriceModel>,
    val thumbnail: ThumbnailModel,
    val images:List<ImageModel>
)