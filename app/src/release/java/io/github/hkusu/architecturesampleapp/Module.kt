package io.github.hkusu.architecturesampleapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hkusu.architecturesampleapp.data.di.NetworkInterceptorsDIWrapper

@Module
@InstallIn(SingletonComponent::class)
object ReleaseModule {

    @Provides
    fun provideNetworkInterceptors(): NetworkInterceptorsDIWrapper {
        return object : NetworkInterceptorsDIWrapper {
            override val interceptors: List<Any> = emptyList()
        }
    }
}
