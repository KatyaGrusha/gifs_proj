package com.dev.gifapp.ui.detail

import androidx.fragment.app.viewModels
import com.dev.gifapp.data.dataBase.entity.GifDbModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.dev.gifapp.databinding.FragmentGifDetailBinding
import com.dev.gifapp.ui.base.BaseFragment
import com.dev.gifapp.extensions.parcelable
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifDetailFragment :
    BaseFragment<FragmentGifDetailBinding>(FragmentGifDetailBinding::inflate) {
    private val viewModel: GifDetailViewModel by viewModels()

    override fun setUpViews() {
        super.setUpViews()
        when (val gifMedia = arguments?.parcelable<MediaAbstractModel>("gifMedia")) {
            is MediaModel -> initGifById(gifMedia.id)
            is GifDbModel -> initGifByCache(gifMedia.uri)
        }
    }

    private fun initGifById(id: String) {
        binding.gifsMediaView.setMediaWithId(id)
    }

    private fun initGifByCache(uri: String) {
        val controller: DraweeController =
            Fresco.newDraweeControllerBuilder().setUri(uri)
                .setAutoPlayAnimations(true).build()
        binding.gifsMediaView.controller = controller
    }
}