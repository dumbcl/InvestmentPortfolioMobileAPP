package com.example.investmentportfolio.data

import com.example.investmentportfolio.data.network.ApiRepository
import com.example.investmentportfolio.data.network.ApiResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PortfolioRepositoryImpl(val apiRepository: ApiRepository): PortfolioRepository {

    private val stocksInformation : MutableList<SearchStockItem> = mutableListOf()
    private val userPortfolios: MutableList<PortfolioItem> = mutableListOf()

    override suspend fun loadPortfolios() = flow {
        try {
            val result = apiRepository.getUserPortfolios()
            setPortfolios(result)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load portfolios"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createPortfolio(id: Int, name: String) = flow {
        try {
            apiRepository.createPortfolio(id, name)
            emit(ApiResultState.OnSuccess("success"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to create portfolio"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun buyStock(id: Int, number: Int) = flow {
        try {
            apiRepository.buyStock(id, number)
            emit(ApiResultState.OnSuccess("success"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to buy stock"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun sellStock(id: Int, number: Int) = flow {
        try {
            apiRepository.sellStock(id, number)
            emit(ApiResultState.OnSuccess("success"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to sell stock"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadPortfolio(portfolioId: Int) = flow {
        try {
            val result = apiRepository.getPortfolio(portfolioId)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load portfolio"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadStocksInPortfolio(portfolioId: Int) = flow {
        try {
            val result = apiRepository.getStocksInPortfolio(portfolioId)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load stocks in portfolio"))
        }
    }.flowOn(Dispatchers.IO)

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

    private fun setPortfolios(loadedPortfolios: List<PortfolioItem>) {
        userPortfolios.clear()
        userPortfolios.addAll(loadedPortfolios)
    }

}