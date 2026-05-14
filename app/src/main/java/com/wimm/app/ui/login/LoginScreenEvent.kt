package com.wimm.app.ui.login

sealed interface LoginScreenEvent {
    data object OnSignInClick: LoginScreenEvent
}