package com.dev.gifapp.data.dataBase.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "gifs", indices = [Index(value = ["uri"], unique = true)])
data class GifDbModel(
    val uri: String,
    @PrimaryKey(autoGenerate = true) val gifId: Int = 0
): MediaAbstractModel()