package com.dev.gifapp.data.localApi

import com.dev.gifapp.data.models.GifsModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.PaginationModel

interface IGifsLocalApi {
    fun saveData(model: GifsModel)
    fun getCurrentGifsList(): List<MediaModel>
    fun getPaginationModel(): PaginationModel?
    fun clear()
}