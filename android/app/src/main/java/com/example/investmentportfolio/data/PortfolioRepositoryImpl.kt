package com.example.investmentportfolio.data

import com.example.investmentportfolio.data.network.ApiRepository
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PortfolioRepositoryImpl(val apiRepository: ApiRepository): PortfolioRepository {

    private val stocksInformation : MutableList<SearchStockItem> = mutableListOf()

    override suspend fun getPortfolios(): List<PortfolioItem> {
        TODO("Not yet implemented")
    }

    override suspend fun createPortfolio(name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getStocksInPortfolio(portfolioId: Int): List<StockItem> {
        TODO("Not yet implemented")
    }

    override suspend fun discardStocks(id: Int, number: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun investStocks(id: Int, number: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun loadStockInformation(id: Int): SearchStockItem {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllStocksInformation() = flow {
        try {
            val result = apiRepository.getAllStocksInformation()
            setStocksInformation(result)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load stocks"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadOperationsHistory() = flow {
            try {
                val result = apiRepository.getOperationsHistory()
                emit(ApiResultState.OnSuccess(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResultState.OnFailure(e.message ?: "Failed to load operations"))
            }
        }.flowOn(Dispatchers.IO)

    override fun getStocksInformation(): List<SearchStockItem> = stocksInformation

    override suspend fun getStockInformation(id: Int): SearchStockItem = stocksInformation.first { it.id == id }

    override suspend fun getStocksByName(name: String): List<SearchStockItem> = stocksInformation.filter {it.name.startsWith(name, true)}


    private fun setStocksInformation(loadedStocksInformation: List<SearchStockItem>) {
        stocksInformation.clear()
        stocksInformation.addAll(loadedStocksInformation)
    }

}