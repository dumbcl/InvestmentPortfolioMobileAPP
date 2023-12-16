package com.example.investmentportfolio.data

import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun getPortfolios(): List<PortfolioItem>
    suspend fun createPortfolio(name: String)

    suspend fun getStocksInPortfolio(portfolioId: Int): List<StockItem>

    suspend fun discardStocks(id: Int, number: Int)
    suspend fun investStocks(id: Int, number: Int)

    suspend fun loadStockInformation(id: Int): SearchStockItem

    suspend fun loadAllStocksInformation(): Flow<ApiResultState>

    suspend fun loadOperationsHistory(): Flow<ApiResultState>

    suspend fun getStocksByName(name: String): List<SearchStockItem>

    fun getStocksInformation(): List<SearchStockItem>

    suspend fun getStockInformation(id: Int): SearchStockItem
}