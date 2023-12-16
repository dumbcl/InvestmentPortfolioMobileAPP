package com.example.investmentportfolio.ui.search_screen

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.SearchStockItem

data class SearchStocksScreenState(
    var isLoading: Boolean,
    var isError: Boolean,
    var isSearchEmpty: Boolean,
    var stocks: List<SearchStockItem>,
)