package io.github.hkusu.architecturesampleapp.model.usecase

import io.github.hkusu.architecturesampleapp.model.User
import io.github.hkusu.architecturesampleapp.model.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject internal constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(): List<User> {
        return userRepository.getUserList()
    }
}
