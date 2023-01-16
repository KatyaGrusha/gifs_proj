package com.dev.gifapp.helpers

import android.content.Context
import com.dev.gifapp.LinkData
import com.dev.gifapp.data.dataBase.repository.GifDbRepository
import com.dev.gifapp.data.dataBase.entity.GifDbModel
import com.facebook.cache.common.CacheKey
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker
import com.facebook.imagepipeline.cache.MemoryCache
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.GiphyFrescoHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiphyHelper @Inject constructor(
    @ApplicationContext appContext: Context,
    private val gifDbRepository: GifDbRepository
) {
    init {
        initCache(appContext)
    }

    private fun initCache(context: Context) {
        Giphy.configure(
            context,
            LinkData.GIPHY_ANDROID_SDK_KEY,
            false,
            frescoHandler = object : GiphyFrescoHandler {
                override fun handle(imagePipelineConfigBuilder: ImagePipelineConfig.Builder) {
                    imagePipelineConfigBuilder
                        .apply {
                            setImageCacheStatsTracker(
                                object : ImageCacheStatsTracker {
                                    override fun onBitmapCachePut(cacheKey: CacheKey?) {
                                    }

                                    override fun onBitmapCacheHit(cacheKey: CacheKey?) {
                                    }

                                    override fun onBitmapCacheMiss(cacheKey: CacheKey?) {
                                    }

                                    override fun onMemoryCachePut(cacheKey: CacheKey?) {
                                    }

                                    override fun onMemoryCacheHit(cacheKey: CacheKey?) {
                                        cacheKey?.let {
                                            gifDbRepository.insertGifCache(GifDbModel(it.uriString))
                                        }
                                    }

                                    override fun onMemoryCacheMiss(cacheKey: CacheKey?) {
                                    }

                                    override fun onStagingAreaHit(cacheKey: CacheKey?) {
                                    }

                                    override fun onStagingAreaMiss(cacheKey: CacheKey?) {
                                    }

                                    override fun onDiskCacheHit(cacheKey: CacheKey?) {
                                    }

                                    override fun onDiskCacheMiss(cacheKey: CacheKey?) {
                                    }

                                    override fun onDiskCacheGetFail(cacheKey: CacheKey?) {
                                    }

                                    override fun onDiskCachePut(cacheKey: CacheKey?) {
                                    }

                                    override fun registerBitmapMemoryCache(bitmapMemoryCache: MemoryCache<*, *>?) {
                                    }

                                    override fun registerEncodedMemoryCache(encodedMemoryCache: MemoryCache<*, *>?) {
                                    }
                                }
                            )
                        }
                }

                override fun handle(okHttpClientBuilder: OkHttpClient.Builder) {
                }
            })
    }
}