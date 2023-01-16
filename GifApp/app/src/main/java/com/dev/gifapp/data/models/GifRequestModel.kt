package com.dev.gifapp.data.models

import com.dev.gifapp.Constants
import com.dev.gifapp.Constants.LANG_DEFAULT
import com.dev.gifapp.Constants.RATING_DEFAULT

data class GifRequestModel(
    val key: String = Constants.KEY_FOR_SEARCH,
    val count: Int = Constants.COUNT_GIFS,
    val offset: Int = 0,
    val rating: String = RATING_DEFAULT,
    val language: String = LANG_DEFAULT
)
