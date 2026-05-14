package com.wimm.app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.wimm.app.domain.model.User
import kotlinx.coroutines.flow.map

class UserPreferences(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val KEY_ID = stringPreferencesKey("user_id")
        val KEY_NAME = stringPreferencesKey("user_name")
        val KEY_EMAIL = stringPreferencesKey("user_email")
        val KEY_PICTURE = stringPreferencesKey("user_picture")
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[KEY_ID] = user.id
            preferences[KEY_NAME] = user.displayName ?: ""
            preferences[KEY_EMAIL] = user.email ?: ""
            preferences[KEY_PICTURE] = user.profilePictureUri ?: ""
        }
    }

    val userFlow = dataStore.data.map {
        User(
            id = it[KEY_ID] ?: "",
            displayName = it[KEY_NAME],
            email = it[KEY_EMAIL],
            profilePictureUri = it[KEY_PICTURE]
        )
    }

    suspend fun clearUser() {
        dataStore.edit {
            it.clear()
        }
    }
}