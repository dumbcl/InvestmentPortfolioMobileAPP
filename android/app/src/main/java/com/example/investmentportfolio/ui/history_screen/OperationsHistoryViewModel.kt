package com.example.investmentportfolio.ui.history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OperationsHistoryViewModel(val repository: PortfolioRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(OperationsHistoryScreenState(
        isLoading = true,
        isError = false,
        operations = listOf()
    ))

    val uiState = _uiState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            repository.loadOperationsHistory().onStart {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = true,
                        isError = false,
                        operations = listOf()
                    )
                }
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = false,
                        isError = true,
                        operations = listOf()
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val operations = it.data as List<OperationItem>
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = false,
                                operations = operations
                            )
                        }
                    }
                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                operations = listOf()
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
}