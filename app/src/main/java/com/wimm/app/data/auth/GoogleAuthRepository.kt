package com.wimm.app.data.auth

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.wimm.app.BuildConfig
import com.wimm.app.data.local.UserPreferences
import com.wimm.app.domain.model.AuthResult
import com.wimm.app.domain.model.User
import com.wimm.app.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleAuthRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userPreferences: UserPreferences
) : AuthRepository {

    private val credentialManager = CredentialManager.create(context)
    private var currentUser: User? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            userPreferences.userFlow.collect {
                currentUser = it
            }
        }
    }
    override suspend fun signIn(): AuthResult {
        return try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
                .setAutoSelectEnabled(false)
                .build()

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(
                request = request,
                context = context
            )

            val credential = result.credential
            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val user = User(
                    id = googleCredential.id,
                    displayName = googleCredential.displayName,
                    email = googleCredential.id,
                    profilePictureUri = googleCredential.profilePictureUri?.toString()
                )
                currentUser = user
                userPreferences.saveUser(user)
                AuthResult.Success(user)
            } else {
                AuthResult.Error("Unexpected credential type")
            }
        } catch (e: GetCredentialException) {
            Log.d("login", "${e.errorMessage}")

            AuthResult.Cancelled
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun signOut() {
        currentUser = null
        userPreferences.clearUser()
    }

    override fun getCurrentUser(): User? = currentUser

    override fun isLoggedIn(): Boolean = currentUser != null
}