package com.example.investmentportfolio.ui.stock_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.SearchStockItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StockViewModel(val repository: PortfolioRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(
        StockScreenState(
            stock = SearchStockItem("-1", "", 0f, "", "", "", "")
        )
    )

    val uiState = _uiState.asStateFlow()

    fun init(id: String) {
        viewModelScope.launch {
            val stock = repository.getStockInformation(id)
            _uiState.update {
                uiState.value.copy(
                    stock = stock
                )
            }
        }
    }

}