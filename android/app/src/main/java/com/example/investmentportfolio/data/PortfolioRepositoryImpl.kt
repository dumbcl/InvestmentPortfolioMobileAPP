package com.example.investmentportfolio.data

import android.content.SharedPreferences
import com.example.investmentportfolio.data.network.ApiRepository
import com.example.investmentportfolio.data.network.ApiResultState
import com.example.investmentportfolio.data.network.CreatePortfolioRequest
import com.example.investmentportfolio.data.network.NWUserLoginRequest
import com.example.investmentportfolio.data.network.PortfolioResponseData
import com.example.investmentportfolio.data.network.PurchaseStockRequest
import com.example.investmentportfolio.data.network.PurchasedStockResponseData
import com.example.investmentportfolio.data.network.StockInformationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Current implementation of Portfolio Repository Interface
 *
 *
 * @property apiRepository is the api interface.
 */
class PortfolioRepositoryImpl(val apiRepository: ApiRepository, val sharedPreferences: SharedPreferences): PortfolioRepository {

    /**
     * The variable containing all stocks information.
     */
    private val stocksInformation : MutableList<SearchStockItem> = mutableListOf()
    /**
     * The variable containing user portfolios.
     */
    private val userPortfolios: MutableList<PortfolioItem> = mutableListOf()

    override fun getName(): String{
        return sharedPreferences.getString("USERNAME", "Name")!!
    }

    override suspend fun register(login: String, password: String) = flow {
        try {
            val result = apiRepository.register(NWUserLoginRequest(login, password))
            //setPortfolios(result)
            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to register"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun login(login: String, password: String) = flow {
        try {
            val result = apiRepository.login(NWUserLoginRequest(login, password))
            //setPortfolios(result)

            sharedPreferences.edit().putString("USERNAME", login).apply()
            sharedPreferences.edit().putString("AUTH_TOKEN", result.body()?.data?.token).apply()
            sharedPreferences.edit().putInt("USER_ID", result.body()?.data?.userId!!).apply()

            emit(ApiResultState.OnSuccess(result))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to login"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadPortfolios() = flow {
        try {
            val result = apiRepository.getUserPortfolios(sharedPreferences.getInt("USER_ID", 0).toString())
            val convertedResult = result.body()?.data?.map { convertToUiPortfolio(it) }
            if (convertedResult != null) {
                setPortfolios(convertedResult)
            }
            emit(ApiResultState.OnSuccess(convertedResult))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load portfolios"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createPortfolio(id: Int, name: String) = flow {
        try {
            apiRepository.createPortfolio(CreatePortfolioRequest(name, sharedPreferences.getInt("USER_ID", 0).toString()))
            emit(ApiResultState.OnSuccess("success"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to create portfolio"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun buyStock(id: String, number: Int, portfolioId: Int) = flow {
        try {
            val stock = getStockInformation(id)
            apiRepository.buyStock(PurchaseStockRequest(
                boughtPrice = stock.price,
                figi = stock.figi,
                name = stock.name,
                number = number,
                portfolioStockId = portfolioId.toString(),
                ticker = stock.ticker
            ))
            emit(ApiResultState.OnSuccess("success"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to buy stock"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun sellStock(id: Int) = flow {
        try {
            apiRepository.sellStock(id)
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

            val convertedResult = result.body()?.map {convertToUiSearchStock(it)}
            if (convertedResult != null) {
                setStocksInformation(convertedResult)
            }
            emit(ApiResultState.OnSuccess(convertedResult))
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

    override suspend fun getPortfolio(id: Int) = flow {
        try {
            val result = apiRepository.getPortfolio(id)
            val convertedResult = result.body()?.data?.let { convertToUiPortfolio(it) }
            emit(ApiResultState.OnSuccess(convertedResult))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiResultState.OnFailure(e.message ?: "Failed to load operations"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getStocksInformation(): List<SearchStockItem> = stocksInformation

    override suspend fun getStockInformation(id: String): SearchStockItem = stocksInformation.first { it.id == id }

    override suspend fun getStocksByName(name: String): List<SearchStockItem> = stocksInformation.filter {it.name.startsWith(name, true)}


    private fun setStocksInformation(loadedStocksInformation: List<SearchStockItem>) {
        stocksInformation.clear()
        stocksInformation.addAll(loadedStocksInformation)
    }

    private fun setPortfolios(loadedPortfolios: List<PortfolioItem>) {
        userPortfolios.clear()
        userPortfolios.addAll(loadedPortfolios)
    }

    private fun convertToUiSearchStock(stock: StockInformationResponse): SearchStockItem {
        return SearchStockItem(
            id = stock.figi,
            name = stock.name,
            price = stock.price,
            country = stock.type,
            companyName = stock.currency,
            figi = stock.figi,
            ticker = stock.ticker
        )
    }

    private fun convertToUiPortfolio(portfolio: PortfolioResponseData): PortfolioItem {
        return PortfolioItem(
            id = portfolio.id,
            name = portfolio.name,
            creationDate = portfolio.createdDate.split(".")[0],
            moneyNumber = portfolio.value,
            profitPercent = portfolio.profit,
            stocks = portfolio.purchasedStocks.map { convertToUiStock(it) }
        )
    }

    private fun convertToUiStock(stock: PurchasedStockResponseData): StockItem {
        return StockItem(
            id = stock.id,
            name = stock.name,
            price = stock.curPrice,
            stockNumber = stock.number,
            profitPercent = 1f,
            stockId = stock.figi,
        )
    }

}