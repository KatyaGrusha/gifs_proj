package com.dev.gifapp.data.repository

import com.dev.gifapp.data.requesters.GifsRequester
import com.dev.gifapp.data.localApi.GifsLocalApi
import com.dev.gifapp.data.models.GifRequestModel
import com.dev.gifapp.data.models.GifsModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.PaginationModel
import com.dev.gifapp.data.network.NetworkResult
import javax.inject.Inject

class GifsRepository @Inject constructor(
    private val gifsRequester: GifsRequester,
    private val gifsLocalApi: GifsLocalApi
) : IGifsRepository {

    override suspend fun getGifsListByKey(key: String): NetworkResult<List<MediaModel>> {
        val response = gifsRequester.sendRequest(GifRequestModel(key))
        if (response is NetworkResult.Success) {
            response.data?.let {
                saveDataInLocalApi(it)
                return NetworkResult.Success(it.data ?: listOf())
            }
            return NetworkResult.Success(listOf())
        }
        return NetworkResult.Error()
    }

    override suspend fun loadNextGifs(requestModel: GifRequestModel): List<MediaModel> {
        val response = gifsRequester.sendRequest(requestModel)
        if (response is NetworkResult.Success) {
            response.data?.let {
                gifsLocalApi.saveData(it)
                return it.data ?: listOf()
            }
        }
        return listOf()
    }

    override fun getPaginationModel(): PaginationModel? {
        return gifsLocalApi.getPaginationModel()
    }

    override fun isLastPagination(): Boolean {
        val model = gifsLocalApi.getPaginationModel() ?: return true
        if ((model.offset + model.count) >= model.totalCount) return true
        return false
    }

    private fun saveDataInLocalApi(data: GifsModel) {
        gifsLocalApi.clear()
        gifsLocalApi.saveData(data)
    }
}