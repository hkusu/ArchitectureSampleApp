package io.github.hkusu.architecturesampleapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hkusu.architecturesampleapp.common.Config
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object appModule {

    @Singleton
    @Provides
    fun provideConfig(): Config {
        return object : Config {
            override val isDebug: Boolean = BuildConfig.DEBUG
        }
    }
}
