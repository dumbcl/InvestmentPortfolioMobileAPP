package com.example.investmentportfolio.data

import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.flow.Flow

interface PortfolioRepository {
    suspend fun loadPortfolios(): Flow<ApiResultState>
    suspend fun createPortfolio(id: Int, name: String): Flow<ApiResultState>
    suspend fun buyStock(id: Int, number: Int): Flow<ApiResultState>
    suspend fun sellStock(id: Int, number: Int): Flow<ApiResultState>
    suspend fun loadPortfolio(portfolioId: Int): Flow<ApiResultState>
    suspend fun loadStocksInPortfolio(portfolioId: Int): Flow<ApiResultState>

    suspend fun discardStocks(id: Int, number: Int)
    suspend fun investStocks(id: Int, number: Int)

    suspend fun loadStockInformation(id: Int): SearchStockItem

    suspend fun loadAllStocksInformation(): Flow<ApiResultState>

    suspend fun loadOperationsHistory(): Flow<ApiResultState>

    suspend fun getStocksByName(name: String): List<SearchStockItem>

    fun getStocksInformation(): List<SearchStockItem>

    suspend fun getStockInformation(id: Int): SearchStockItem
}