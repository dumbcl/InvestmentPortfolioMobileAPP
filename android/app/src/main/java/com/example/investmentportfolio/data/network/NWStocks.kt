package com.example.investmentportfolio.data.network


data class StockInformationResponse(
    val currency: String,
    val figi: String,
    val name: String,
    val price: Float,
    val source: String,
    val ticker: String,
    val type: String,
)