package com.dev.gifapp.domain.usecases

import com.dev.gifapp.data.models.GifRequestModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.network.NetworkResult
import com.dev.gifapp.data.repository.IGifsRepository
import com.dev.gifapp.domain.usecases.interfaces.IGifsListUseCase
import javax.inject.Inject

class GifsListUseCase @Inject constructor(
    private val gifsRepository: IGifsRepository
) : IGifsListUseCase {

    override suspend fun getGifsListByKey(key: String): NetworkResult<List<MediaModel>> {
        return gifsRepository.getGifsListByKey(key)
    }

    override suspend fun loadNextGifs(key: String): List<MediaModel> {
        if (gifsRepository.isLastPagination()) return listOf()
        gifsRepository.getPaginationModel()?.let {
            return gifsRepository.loadNextGifs(
                GifRequestModel(key, it.count, it.offset + it.count))
        }
        return listOf()
    }
}