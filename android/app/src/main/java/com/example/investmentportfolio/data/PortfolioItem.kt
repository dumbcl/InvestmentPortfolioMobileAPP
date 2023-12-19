package com.example.investmentportfolio.data

data class PortfolioItem (val id: Int, val name: String, val creationDate: String, val moneyNumber: Float, val profitPercent: Float,
    val stocks: List<StockItem>)