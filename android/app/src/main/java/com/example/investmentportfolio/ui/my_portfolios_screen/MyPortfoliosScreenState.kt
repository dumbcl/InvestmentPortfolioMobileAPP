package com.example.investmentportfolio.ui.my_portfolios_screen

import com.example.investmentportfolio.data.PortfolioItem

data class MyPortfoliosScreenState (
    var isLoading: Boolean,
    var isError: Boolean,
    var isCreateDialogShown: Boolean,
    var isSuccessDialogShown: Boolean,
    var portfolios: List<PortfolioItem>,
    var name: String,
)