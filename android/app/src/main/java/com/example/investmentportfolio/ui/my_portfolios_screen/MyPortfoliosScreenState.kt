package com.example.investmentportfolio.ui.my_portfolios_screen

sealed class MyPortfoliosScreenState {
    object Init: MyPortfoliosScreenState()
    object Loading: MyPortfoliosScreenState()
    object Error: MyPortfoliosScreenState()
}