package com.wimm.app.domain.repository

import com.wimm.app.domain.model.AuthResult
import com.wimm.app.domain.model.User

interface AuthRepository {
    suspend fun signIn(): AuthResult
    suspend fun signOut()
    fun getCurrentUser(): User?
    fun isLoggedIn(): Boolean
}