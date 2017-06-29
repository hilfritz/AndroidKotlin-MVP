package com.hilfritz.samplekotlin.dagger.modules

import com.hilfritz.samplekotlin.MyApplication
import com.hilfritz.samplekotlin.util.ImageCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Hilfritz Camallere on 24/6/17.
 */
@Module(includes = arrayOf(SessionModule::class))
class CacheModule {
    @Singleton
    @Provides
    internal fun provideImageCache(myApplication: MyApplication): ImageCache {
        return ImageCache(myApplication)
    }
}
