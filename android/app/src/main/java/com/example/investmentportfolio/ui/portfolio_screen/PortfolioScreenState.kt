package com.example.investmentportfolio.ui.portfolio_screen

import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem

data class PortfolioScreenState (
    var isLoadingPortfolio: Boolean,
    var isLoadingStocks: Boolean,
    var isPortfolioError: Boolean,
    var isStocksError: Boolean,
    var isStocksInformationError: Boolean,
    var portfolio: PortfolioItem,
    var stocks: List<StockItem>,
    var stocksInformation: List<SearchStockItem>,
    var isBuyDialogShown: Boolean,
    var isSellDialogShown: Boolean,
    var isSuccessBuyDialogShown: Boolean,
    var isSuccessSellDialogShown: Boolean,
    var isDropDownShown: Boolean,
    var clickedStockToSell: StockItem?,
)