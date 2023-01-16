package com.dev.gifapp.data.models

import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class GifsModel(
    val data: List<MediaModel>?,
    val pagination: PaginationModel?
)
@Parcelize
@JsonClass(generateAdapter = true)
data class MediaModel(
    val id: String
): MediaAbstractModel()

@JsonClass(generateAdapter = true)
data class PaginationModel(
    @Json(name = "total_count") val totalCount: Int,
    val count: Int,
    val offset: Int
)