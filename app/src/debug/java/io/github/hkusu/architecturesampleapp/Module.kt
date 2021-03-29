package io.github.hkusu.architecturesampleapp

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hkusu.architecturesampleapp.data.di.NetworkInterceptorsDIWrapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DebugModule {

    @Provides
    fun provideNetworkInterceptors(networkFlipperPlugin: NetworkFlipperPlugin): NetworkInterceptorsDIWrapper {
        return object : NetworkInterceptorsDIWrapper {
            override val interceptors: List<Any> = listOf(
                FlipperOkhttpInterceptor(networkFlipperPlugin)
            )
        }
    }

    @Singleton // 同一インスタンスをFlipperとOkHttpで共有する必要がある
    @Provides
    fun provideNetworkFlipperPlugin(): NetworkFlipperPlugin {
        // data モジュールに debug ビルドで Flipper を導入してもよいが煩雑になるので app モジュールで持つ
        // debug/release 分けやデバッグ用のライブラリを扱うのは app モジュールの責務とする
        return NetworkFlipperPlugin()
    }
}
