package io.github.hkusu.architecturesampleapp.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
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

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    fun provideGitHubApi(): GitHubApi {
        return GitHubApi(Libs.httpClient)
    }
}

internal val Context.mainDataStore by preferencesDataStore("main")

private object Libs {

    // appモジュールからKtorのHttpClientクラスを参照できないのでDaggerの@Providesアノテーションでは配布しない
    // singleton
    val httpClient: HttpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    // singleton にしない場合
    // val httpClient: HttpClient
    //     get() {
    //         return HttpClient {
    //             install(JsonFeature) {
    //                 serializer = KotlinxSerializer(json = Json {
    //                     ignoreUnknownKeys = true
    //                 })
    //             }
    //         }
    //     }
}
