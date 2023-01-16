package com.dev.gifapp.data.dataBase.repository

import com.dev.gifapp.data.dataBase.entity.GifDbModel
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import kotlinx.coroutines.flow.Flow

interface IGifDbRepository {
    fun insertGifCache(cache: GifDbModel)
    fun getAll(): Flow<List<MediaAbstractModel>>
}