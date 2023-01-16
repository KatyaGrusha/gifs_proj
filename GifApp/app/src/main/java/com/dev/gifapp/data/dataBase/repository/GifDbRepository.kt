package com.dev.gifapp.data.dataBase.repository

import com.dev.gifapp.data.dataBase.dao.GifDao
import com.dev.gifapp.data.dataBase.entity.GifDbModel
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifDbRepository @Inject constructor(
    private val gifDao: GifDao
) : IGifDbRepository {
    override fun insertGifCache(cache: GifDbModel) {
        gifDao.insertGifCache(cache)
    }

    override fun getAll(): Flow<List<MediaAbstractModel>> {
        return gifDao.getAll()
    }
}
