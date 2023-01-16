package com.dev.gifapp.data.repository

import com.dev.gifapp.data.models.GifRequestModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.PaginationModel
import com.dev.gifapp.data.network.NetworkResult

interface IGifsRepository {
    suspend fun getGifsListByKey(key: String): NetworkResult<List<MediaModel>>
    suspend fun loadNextGifs(requestModel: GifRequestModel): List<MediaModel>
    fun getPaginationModel(): PaginationModel?
    fun isLastPagination(): Boolean
}