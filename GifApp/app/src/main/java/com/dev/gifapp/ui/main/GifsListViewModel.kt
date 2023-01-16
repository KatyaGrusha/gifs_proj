package com.dev.gifapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.gifapp.Constants
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.dev.gifapp.data.network.NetworkResult
import com.dev.gifapp.domain.usecases.interfaces.IGifListDbUseCase
import com.dev.gifapp.domain.usecases.interfaces.IGifsListUseCase
import com.dev.gifapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class GifsListViewModel @Inject constructor(
    private val gifsListUseCase: IGifsListUseCase,
    private val gifListDbUseCase: IGifListDbUseCase
) : BaseViewModel() {
    private val _gifsList = MutableLiveData<List<MediaAbstractModel>?>()
    val gifsList: LiveData<List<MediaAbstractModel>?>
        get() = _gifsList

    private val _gifsSearchKey = MutableLiveData<String>()

    companion object {
        private const val KEY_DEFAULT = Constants.KEY_FOR_SEARCH
    }

    fun initList() = flow {
        _gifsSearchKey.value = KEY_DEFAULT
        when (val result = gifsListUseCase.getGifsListByKey(KEY_DEFAULT)) {
            is NetworkResult.Success -> {
                _gifsList.value = result.data
                emit(result.data)
            }
            is NetworkResult.Error -> {
                gifListDbUseCase.getDbData().collect {
                    _gifsList.value = it
                    emit(it)
                }
            }
        }
    }

    fun search(key: String) = flow {
        _gifsSearchKey.value = key
        when (val result = gifsListUseCase.getGifsListByKey(key)) {
            is NetworkResult.Success -> {
                _gifsList.value = result.data
                emit(result.data)
            }
            is NetworkResult.Error -> {
                _gifsList.value = listOf()
                emit(listOf())
            }
        }
    }

    fun loadData() = flow {
        _gifsSearchKey.value?.let {
            val loadedList = gifsListUseCase.loadNextGifs(it)
            _gifsList.value = _gifsList.value?.let { list1 -> list1 + loadedList }
            emit(loadedList)
        }
    }
}