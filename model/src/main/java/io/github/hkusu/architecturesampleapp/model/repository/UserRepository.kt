package io.github.hkusu.architecturesampleapp.model.repository

import io.github.hkusu.architecturesampleapp.model.User

interface UserRepository {
    suspend fun getUserList(): List<User>
}
