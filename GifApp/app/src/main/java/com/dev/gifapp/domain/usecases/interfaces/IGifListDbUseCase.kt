package com.dev.gifapp.domain.usecases.interfaces

import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import kotlinx.coroutines.flow.Flow

interface IGifListDbUseCase {
    fun getDbData(): Flow<List<MediaAbstractModel>>
}