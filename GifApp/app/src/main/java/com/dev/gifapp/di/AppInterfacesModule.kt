package com.dev.gifapp.di

import com.dev.gifapp.data.dataBase.repository.GifDbRepository
import com.dev.gifapp.data.dataBase.repository.IGifDbRepository
import com.dev.gifapp.data.localApi.GifsLocalApi
import com.dev.gifapp.data.localApi.IGifsLocalApi
import com.dev.gifapp.data.repository.GifsRepository
import com.dev.gifapp.data.repository.IGifsRepository
import com.dev.gifapp.domain.usecases.GifListDbUseCase
import com.dev.gifapp.domain.usecases.GifsListUseCase
import com.dev.gifapp.domain.usecases.interfaces.IGifListDbUseCase
import com.dev.gifapp.domain.usecases.interfaces.IGifsListUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInterfacesModule {
    //region LocalApi
    @Binds
    abstract fun bindGifsLocalApi(impl: GifsLocalApi): IGifsLocalApi
    //endregion

    //region Repositories
    @Binds
    abstract fun bindGifsRepository(impl: GifsRepository): IGifsRepository

    @Binds
    abstract fun bindGifDbRepository(impl: GifDbRepository): IGifDbRepository

    //endregion

    //region UseCase
    @Binds
    abstract fun bindGifsListUseCase(impl: GifsListUseCase): IGifsListUseCase

    @Binds
    abstract fun bindGifListDbUseCase(impl: GifListDbUseCase): IGifListDbUseCase
    //endregion
}