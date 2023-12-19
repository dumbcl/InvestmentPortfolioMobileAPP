package com.example.investmentportfolio.ui.portfolio_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


/**
 * Viewmodel of Portfolio screen
 *
 * @property repository is the interface of interaction with data layer.
 *
 */
class PortfolioViewModel(val repository: PortfolioRepository) : ViewModel() {

    /**
     * The variable containing id of current portfolio (changes in Fragment on create view method).
     */
    var id = -1

    /**
     * Saving state.
     */
    private val _uiState = MutableStateFlow(
        PortfolioScreenState(
            isLoadingPortfolio = true,
            isLoadingStocks = false,
            isPortfolioError = false,
            isStocksError = false,
            isStocksInformationError = false,
            stocks = listOf(),
            stocksInformation = listOf(),
            portfolio = PortfolioItem(0, "a", "b", 1.0f, 2.0f, listOf()),
            isBuyDialogShown = false,
            isSellDialogShown = false,
            isSuccessBuyDialogShown = false,
            isSuccessSellDialogShown = false,
            isDropDownShown = false,
            clickedStockToSell = null,
            lastChosenStock = null,
        )
    )

    val uiState = _uiState.asStateFlow()

    /**
     * Loading portfolio information from repository.
     */
    fun init() {
        viewModelScope.launch {
            repository.getPortfolio(id).onStart {
                _uiState.update {
                    uiState.value.copy(
                        isLoadingPortfolio = true,
                    )
                }
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isLoadingPortfolio = false,
                        isPortfolioError = true,
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val portfolio = it.data as PortfolioItem
                        _uiState.update {
                            uiState.value.copy(
                                isLoadingPortfolio = false,
                                isPortfolioError = false,
                                portfolio = portfolio,
                                stocks = portfolio.stocks
                            )
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isLoadingPortfolio = false,
                                isPortfolioError = true,
                            )
                        }
                    }

                }
            }
        }
        loadStocksInformation()
    }

    private fun loadStocksInformation() {
        viewModelScope.launch {
            repository.loadAllStocksInformation().onStart {
            }.catch {
                _uiState.update {
                    uiState.value.copy(
                        isStocksInformationError = true,
                    )
                }
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        val stocks = it.data as List<SearchStockItem>
                        _uiState.update {
                            uiState.value.copy(
                                isStocksInformationError = false,
                                stocksInformation = stocks
                            )
                        }
                    }

                    is ApiResultState.OnFailure -> {
                        _uiState.update {
                            uiState.value.copy(
                                isStocksInformationError = true,
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

    fun showSellStockDialog(stockItem: StockItem) {
        _uiState.update {
            uiState.value.copy(
                isSellDialogShown = true,
                clickedStockToSell = stockItem,
            )
        }
    }

    fun showBuyStockDialog() {
        _uiState.update {
            uiState.value.copy(
                isBuyDialogShown = true
            )
        }
    }

    fun discardSellStockDialog() {
        _uiState.update {
            uiState.value.copy(
                isSellDialogShown = false,
                clickedStockToSell = null,
            )
        }
    }

    fun discardBuyStockDialog() {
        _uiState.update {
            uiState.value.copy(
                isBuyDialogShown = false,
                lastChosenStock = null
            )
        }
    }

    fun showSuccessBuyDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessBuyDialogShown = true
            )
        }
    }

    fun showSuccessSellDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessSellDialogShown = true
            )
        }
    }

    fun discardSuccessBuyDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessBuyDialogShown = false
            )
        }
    }

    fun discardSuccessSellDialog() {
        _uiState.update {
            uiState.value.copy(
                isSuccessSellDialogShown = false
            )
        }
    }

    fun buyStock(stockId: String, number: Int) {
        viewModelScope.launch {
            repository.buyStock(stockId, number, id).onStart {
            }.catch {
                discardBuyStockDialog()
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        discardBuyStockDialog()
                        showSuccessBuyDialog()
                        delay(2000)
                        discardSuccessBuyDialog()
                        reload()
                    }

                    is ApiResultState.OnFailure -> {
                        discardBuyStockDialog()
                    }
                }
            }
        }
    }

    fun sellStock(stockId: Int) {
        viewModelScope.launch {
            repository.sellStock(stockId).onStart {
            }.catch {
                discardSellStockDialog()
            }.collect {
                when (it) {
                    is ApiResultState.OnSuccess<*> -> {
                        discardSellStockDialog()
                        showSuccessSellDialog()
                        delay(2000)
                        discardSuccessSellDialog()
                        reload()
                    }

                    is ApiResultState.OnFailure -> {
                        discardSellStockDialog()
                    }
                }
            }
        }
    }

    fun searchStocks(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                val stocks = repository.getStocksByName(name)
                if (stocks.isEmpty()) {
                    _uiState.update {
                        uiState.value.copy(
                            stocksInformation = listOf()
                        )
                    }
                } else {
                    _uiState.update {
                        uiState.value.copy(
                            stocksInformation = stocks
                        )
                    }
                }
            }
        } else {
            viewModelScope.launch {
                _uiState.update {
                    uiState.value.copy(
                        stocksInformation = repository.getStocksInformation()
                    )
                }
            }
        }
    }

    fun showDropDown() {
        discardBuyStockDialog()
        _uiState.update {
            uiState.value.copy(
                isDropDownShown = true
            )
        }
        showBuyStockDialog()
    }

    fun discardDropDown() {
        _uiState.update {
            uiState.value.copy(
                isDropDownShown = false
            )
        }
    }

    fun chooseStockFromDropDownMenu(stock: SearchStockItem) {
        _uiState.update {
            uiState.value.copy(
                isDropDownShown = false,
                lastChosenStock = stock
            )
        }
    }

}