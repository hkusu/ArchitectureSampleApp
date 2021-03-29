package io.github.hkusu.architecturesampleapp.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hkusu.architecturesampleapp.data.api.GitHubApi
import io.github.hkusu.architecturesampleapp.data.repository.UserRepositoryImpl
import io.github.hkusu.architecturesampleapp.model.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun provideGitHubApi(httpClientDIWrapper: HttpClientDIWrapper): GitHubApi {
        return GitHubApi(httpClientDIWrapper.httpClient)
    }

    @Singleton
    @Provides
    fun provideHttpClient(networkInterceptorsDIWrapper: NetworkInterceptorsDIWrapper): HttpClientDIWrapper {
        return object : HttpClientDIWrapper {
            override val httpClient: HttpClient = HttpClient(OkHttp) {
                engine {
                    networkInterceptorsDIWrapper.interceptors.forEach { any ->
                        (any as? Interceptor)?.let { interceptor ->
                            addNetworkInterceptor(
                                interceptor
                            )
                        }
                    }
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(json = Json {
                        ignoreUnknownKeys = true
                    })
                }
            }
        }
    }

    // appモジュールから参照できずDIに失敗する為
    interface HttpClientDIWrapper {
        val httpClient: HttpClient
    }
}

// app モジュールで実体を配信
interface NetworkInterceptorsDIWrapper {
    val interceptors: List<Any> // app モジュールからは OkHttp を認識できないので Any を利用
}
