package com.example.investmentportfolio.ui.my_portfolios_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class MyPortfoliosViewModel(val repository: PortfolioRepository): ViewModel() {

    lateinit var navController : NavController

    private val _uiState = MutableStateFlow(
        MyPortfoliosScreenState(
            isLoading = true,
            isError = false,
            isCreateDialogShown = false,
            isSuccessDialogShown = false,
            portfolios = listOf()
        )
    )

    val uiState = _uiState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            repository.loadPortfolios().onStart {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = true,
                        isError = false,
                        isCreateDialogShown = false,
                        isSuccessDialogShown = false,
                        portfolios = listOf()
                    )
                }
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = false,
                        isError = true,
                        isCreateDialogShown = false,
                        isSuccessDialogShown = false,
                        portfolios = listOf()
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val portfolios = it.data as List<PortfolioItem>
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = false,
                                isCreateDialogShown = false,
                                isSuccessDialogShown = false,
                                portfolios = portfolios
                            )
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                isCreateDialogShown = false,
                                isSuccessDialogShown = false,
                                portfolios = listOf()
                            )
                        }
                    }
                }
            }
        }
    }

    fun reload() {
        init()
    }

    fun showCreateDialog() {
        _uiState.update {
            uiState.value.copy(
                isCreateDialogShown = true,
            )
        }
    }

    fun discardCreateDialog() {
        _uiState.update {
            uiState.value.copy(
                isCreateDialogShown = false,
            )
        }
    }

    fun showSuccessDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessDialogShown = true,
            )
        }
    }

    fun discardSuccessDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessDialogShown = false,
            )
        }
    }

    fun navigateToPortfolio(id: Int) {
        navController.navigate(MyPortfoliosFragmentDirections.actionMyPortfoliosFragmentToPortfolioFragment(id))
    }

    fun createPortfolio(name: String) {
        viewModelScope.launch {
            val newId = uiState.value.portfolios.last().id + 1
            repository.createPortfolio(newId, name).onStart {
            }.catch {
                discardCreateDialog()
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        discardCreateDialog()
                        showSuccessDialog()
                        delay(2000)
                        discardSuccessDialog()
                        navigateToPortfolio(newId)
                    }

                    is ApiResultState.OnFailure -> {
                        discardCreateDialog()
                    }
                }
            }
        }
    }
}