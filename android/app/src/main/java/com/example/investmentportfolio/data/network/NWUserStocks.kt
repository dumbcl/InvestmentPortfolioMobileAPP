package com.example.investmentportfolio.data.network

data class PurchasedStockResponse(
    val data: PurchasedStockResponseData,
    val status: PurchasedStockResponseStatus,
)

data class PurchasedStockResponseStatus(
    val message: String,
    val code: Int
)

data class PurchasedStockResponseData(
    val id: Int,
    val name: String,
    val figi: String,
    val ticker: String,
    val number: Int,
    val boughtPrice: Float,
    val curPrice: Float,
    val profit: Float,
    val portfolioStockName: String,
    val createdBy: String,
    val createdDate: String,
    val updatedBy: String,
    val updatedDate: String,
    val deletedAt: String
)

data class PurchaseStockRequest(
    val boughtPrice: Float,
    val figi: String,
    val name: String,
    val number: Int,
    val portfolioStockId: String,
    val ticker: String,
)