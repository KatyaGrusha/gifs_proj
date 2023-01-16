package com.dev.gifapp.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.gifapp.data.dataBase.entity.GifDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGifCache(cache: GifDbModel)

    @Query("SELECT * FROM gifs")
    fun getAll(): Flow<List<GifDbModel>>
}