package com.wimm.app.domain.model

data class User(
    val id: String,
    val displayName: String?,
    val email: String?,
    val profilePictureUri: String?
)