package io.github.hkusu.architecturesampleapp.data.repository

import io.github.hkusu.architecturesampleapp.data.api.GitHubApi
import io.github.hkusu.architecturesampleapp.data.api.response.GitHubUserResponse
import io.github.hkusu.architecturesampleapp.model.User
import io.github.hkusu.architecturesampleapp.model.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(private val gitHubApi: GitHubApi) : UserRepository {

    override suspend fun getUserList(): List<User> {
        return gitHubApi.getUserList().map(GitHubUserResponse::toUser)
    }
}

internal fun GitHubUserResponse.toUser(): User {
    return this.run {
        User(
            name = login
        )
    }
}
