package com.dev.gifapp.domain.usecases.interfaces

import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.network.NetworkResult

interface IGifsListUseCase {
    suspend fun getGifsListByKey(key: String): NetworkResult<List<MediaModel>>
    suspend fun loadNextGifs(key: String): List<MediaModel>
}