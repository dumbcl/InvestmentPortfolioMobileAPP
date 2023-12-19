package com.example.investmentportfolio.data.network

data class CreatePortfolioRequest(
    val name : String,
    val userId : String
)

data class CreatePortfolioResponce(
    val data: PortfolioResponseData,
    val status: PortfolioResponseStatus,
)

data class PortfolioResponse(
    val data: List<PortfolioResponseData>,
    val pages: PortfolioResponsePages,
    val status: PortfolioResponseStatus,
)

data class SinglePortfolioResponse(
    val data: PortfolioResponseData,
    val status: PortfolioResponseStatus,
)

data class PortfolioResponseStatus(
    val message: String,
    val code: Int,
)

data class PortfolioResponsePages(
    val totalPage: Int,
    val page: Int,
    val totalCount: Int,
    val pageSize: Int,
)

data class PortfolioResponseData(
    val id: Int,
    val name: String,
    val value: Float,
    val profit: Float,
    val userName: String,
    val purchasedStocks: List<PurchasedStockResponseData>,
    val createdBy: String,
    val createdDate: String,
    val updatedBy: String,
    val updatedDate: String,
    val deletedAt: String,
)