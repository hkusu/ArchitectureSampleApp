package io.github.hkusu.architecturesampleapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hkusu.architecturesampleapp.data.di.NetworkInterceptors

@Module
@InstallIn(SingletonComponent::class)
object ReleaseModule {

    @Provides
    fun provideNetworkInterceptors(): NetworkInterceptors {
        return object : NetworkInterceptors {
            override val interceptors: List<Any> = emptyList()
        }
    }
}
