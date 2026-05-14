package com.wimm.app.domain.usecase

import com.wimm.app.domain.model.AuthResult
import com.wimm.app.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(): AuthResult = authRepository.signIn()
}