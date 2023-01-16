package com.dev.gifapp.data.requesters

import com.dev.gifapp.data.models.GifRequestModel
import com.dev.gifapp.data.models.GifsModel
import com.dev.gifapp.data.network.NetworkResult
import com.dev.gifapp.data.network.RestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifsRequester @Inject constructor(
    private val restApiService: RestApiService
) {

    suspend fun sendRequest(requestModel: GifRequestModel): NetworkResult<GifsModel?> {
        return withContext(Dispatchers.IO) {
            try {
                val params: MutableMap<String, String> = HashMap()
                params["q"] = requestModel.key
                params["limit"] = requestModel.count.toString()
                params["offset"] = requestModel.offset.toString()
                params["rating"] = requestModel.rating
                params["lang"] = requestModel.language
                val response = restApiService.getGifs(params)
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body())
                } else NetworkResult.Error()
            } catch (ex: Exception) {
                NetworkResult.Error()
            }
        }
    }
}