package com.dev.gifapp.data.localApi

import com.dev.gifapp.data.models.GifsModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.PaginationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifsLocalApi @Inject constructor() : IGifsLocalApi {
    private var listGifs: List<MediaModel>? = listOf()
    private var pagination: PaginationModel? = null

    override fun saveData(model: GifsModel) {
        model.data?.let { listGifs?.toMutableList()?.addAll(it) }
        pagination = model.pagination
    }

    override fun getCurrentGifsList(): List<MediaModel> {
        return listGifs ?: listOf()
    }

    override fun getPaginationModel(): PaginationModel? {
        return pagination
    }

    override fun clear() {
        listGifs = null
        pagination = null
    }
}