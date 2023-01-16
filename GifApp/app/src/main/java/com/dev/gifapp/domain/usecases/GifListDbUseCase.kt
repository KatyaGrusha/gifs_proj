package com.dev.gifapp.domain.usecases

import com.dev.gifapp.data.dataBase.repository.IGifDbRepository
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.dev.gifapp.domain.usecases.interfaces.IGifListDbUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifListDbUseCase @Inject constructor(
    private val gifDbRepository: IGifDbRepository
): IGifListDbUseCase {
    override fun getDbData(): Flow<List<MediaAbstractModel>>{
        return gifDbRepository.getAll()
    }
}