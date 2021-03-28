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
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
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
    fun provideGitHubApi(httpClientWrapper: HttpClientWrapper): GitHubApi {
        return GitHubApi(httpClientWrapper.httpClient)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClientWrapper {
        return HttpClientWrapper(
            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(json = Json {
                        ignoreUnknownKeys = true
                    })
                }
            }
        )
    }

    // appモジュールから参照できずDIに失敗する為
    class HttpClientWrapper(val httpClient: HttpClient)
}
