package com.example.investmentportfolio.ui.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchStocksViewModel(val repository: PortfolioRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchStocksScreenState(
            isLoading = true,
            isError = false,
            isSearchEmpty = false,
            stocks = listOf()
        )
    )

    val uiState = _uiState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            repository.loadAllStocksInformation().onStart {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = true,
                        isError = false,
                        isSearchEmpty = false,
                        stocks = listOf()
                    )
                }
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = false,
                        isError = true,
                        isSearchEmpty = false,
                        stocks = listOf()
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val stocks = it.data as List<SearchStockItem>
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = false,
                                isSearchEmpty = false,
                                stocks = stocks
                            )
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isLoading = false,
                                isError = true,
                                isSearchEmpty = false,
                                stocks = listOf()
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

    fun searchStock(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                val stocks = repository.getStocksByName(name)
                if (stocks.isEmpty()) {
                    _uiState.update {
                        uiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSearchEmpty = true,
                            stocks = listOf()
                        )
                    }
                } else {
                    _uiState.update {
                        uiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSearchEmpty = false,
                            stocks = stocks
                        )
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _uiState.update {
                    uiState.value.copy(
                        isLoading = false,
                        isError = false,
                        isSearchEmpty = true,
                        stocks = repository.getStocksInformation()
                    )
                }
            }
        }
    }
}