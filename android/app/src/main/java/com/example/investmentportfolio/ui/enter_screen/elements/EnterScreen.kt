package com.example.investmentportfolio.ui.enter_screen.elements

import androidx.compose.runtime.Composable
import com.example.investmentportfolio.ui.enter_screen.EnterScreenState

@Composable
fun EnterScreen(
    uiState: EnterScreenState,
    toStartForm: () -> Unit,
    toLoginForm: () -> Unit,
    toRegistrationForm: () -> Unit,
    login: (String, String) -> Unit,
    register: (String, String, String, String) -> Unit,
) {
    if (uiState.isStartFormShown) StartForm(toLoginForm, toRegistrationForm)
    else if (uiState.isLoginFormShown) LoginForm(toStartForm, login, uiState.isLoginError)
    if (uiState.isRegisterFormShown) RegistrationForm(toStartForm, register, uiState.isRegistrationError)
}