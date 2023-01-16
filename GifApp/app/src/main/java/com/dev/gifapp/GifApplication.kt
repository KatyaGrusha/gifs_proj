package com.dev.gifapp

import android.app.Application
import com.dev.gifapp.helpers.GiphyHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GifApplication : Application() {
    @Inject
    lateinit var giphyHelper: GiphyHelper
}
