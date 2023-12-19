package com.example.investmentportfolio.ui.enter_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.network.ApiResultState
import com.example.investmentportfolio.ui.my_portfolios_screen.MyPortfoliosScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EnterViewModel(val repository: PortfolioRepository): ViewModel() {

    lateinit var navController : NavController

    private val _uiState = MutableStateFlow(
        EnterScreenState(
            isStartFormShown = true,
            isLoginFormShown = false,
            isRegisterFormShown = false,
            isLoginError = false,
            isRegistrationError = false
        )
    )

    val uiState = _uiState.asStateFlow()

    fun toLoginForm() {
        _uiState.update {
            uiState.value.copy(
                isStartFormShown = false,
                isLoginFormShown = true,
                isRegisterFormShown = false,
                isLoginError = false,
                isRegistrationError = false
            )
        }
    }

    fun toRegisterForm() {
        _uiState.update {
            uiState.value.copy(
                isStartFormShown = false,
                isLoginFormShown = false,
                isRegisterFormShown = true,
                isLoginError = false,
                isRegistrationError = false
            )
        }
    }

    fun toStartForm() {
        _uiState.update {
            uiState.value.copy(
                isStartFormShown = true,
                isLoginFormShown = false,
                isRegisterFormShown = false,
                isLoginError = false,
                isRegistrationError = false
            )
        }
    }

    fun login(name: String, password: String) {
        viewModelScope.launch {
            repository.login(name, password).onStart {
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isStartFormShown = false,
                        isLoginFormShown = true,
                        isRegisterFormShown = false,
                        isLoginError = true,
                        isRegistrationError = false
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        _uiState.update {
                            uiState.value.copy(
                                isStartFormShown = false,
                                isLoginFormShown = true,
                                isRegisterFormShown = false,
                                isLoginError = false,
                                isRegistrationError = false
                            )
                        }
                        navigateToPortfoliosScreen()
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isStartFormShown = false,
                                isLoginFormShown = true,
                                isRegisterFormShown = false,
                                isLoginError = true,
                                isRegistrationError = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun register(name: String, nameRep: String, password: String, passwordRep: String) {
        viewModelScope.launch {
            repository.register(name, password).onStart {
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isStartFormShown = false,
                        isLoginFormShown = false,
                        isRegisterFormShown = true,
                        isLoginError = false,
                        isRegistrationError = true
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        _uiState.update {
                            uiState.value.copy(
                                isStartFormShown = false,
                                isLoginFormShown = true,
                                isRegisterFormShown = false,
                                isLoginError = false,
                                isRegistrationError = false
                            )
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isStartFormShown = false,
                                isLoginFormShown = false,
                                isRegisterFormShown = true,
                                isLoginError = false,
                                isRegistrationError = true
                            )
                        }
                    }
                }
            }
        }
    }

    fun navigateToPortfoliosScreen() {
        navController.navigate(EnterScreenFragmentDirections.actionEnterFragmentToMyPortfoliosFragment())
    }

}