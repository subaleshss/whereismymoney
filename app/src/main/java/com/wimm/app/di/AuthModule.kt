package com.wimm.app.di

import com.wimm.app.data.auth.GoogleAuthRepository
import com.wimm.app.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: GoogleAuthRepository
    ): AuthRepository
}