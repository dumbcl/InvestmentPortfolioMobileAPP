package com.example.investmentportfolio.data

import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.flow.Flow


/**
 * A repository interaface
 *
 * This interface representing interaction between data layer and viewmodels.
 *
 */
interface PortfolioRepository {
    fun getName(): String

    suspend fun register(login: String, password: String): Flow<ApiResultState>

    suspend fun login(login: String, password: String): Flow<ApiResultState>

    /**
     * Loads portfolios from api.
     * @return ApiResult state either with portfolios or with error.
     */
    suspend fun loadPortfolios(): Flow<ApiResultState>

    /**
     * Creates portfolio with [id] and [name].
     * @return ApiResult state either with success or with error.
     */
    suspend fun createPortfolio(id: Int, name: String): Flow<ApiResultState>

    /**
     * Buys [number] of stocks with [id] from all stocks table.
     * @return ApiResult state either with success or with error.
     */
    suspend fun buyStock(id: String, number: Int, portfolioId: Int): Flow<ApiResultState>

    /**
     * Sells [number] of stocks with [id] from user stocks table.
     * @return ApiResult state either with success or with error.
     */
    suspend fun sellStock(id: Int): Flow<ApiResultState>

    /**
     * Loads portfolio with [portfolioId] from api.
     * @return ApiResult state either with portfolio or with error.
     */
    suspend fun loadPortfolio(portfolioId: Int): Flow<ApiResultState>
    /**
     * Loads stocks from portfolio with [portfolioId] from api.
     * @return ApiResult state either with list of stocks or with error.
     */
    suspend fun loadStocksInPortfolio(portfolioId: Int): Flow<ApiResultState>

    suspend fun discardStocks(id: Int, number: Int)
    suspend fun investStocks(id: Int, number: Int)

    suspend fun loadStockInformation(id: Int): SearchStockItem

    suspend fun loadAllStocksInformation(): Flow<ApiResultState>

    suspend fun loadOperationsHistory(): Flow<ApiResultState>

    suspend fun getStocksByName(name: String): List<SearchStockItem>

    fun getStocksInformation(): List<SearchStockItem>

    suspend fun getStockInformation(id: String): SearchStockItem

    suspend fun getPortfolio(id: Int): Flow<ApiResultState>
}