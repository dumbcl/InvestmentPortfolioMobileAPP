package com.example.investmentportfolio.data.network

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem

class ApiRepositoryImpl: ApiRepository {

    override suspend fun getUserPortfolios(): List<PortfolioItem> {
        return listOf(
            PortfolioItem(
                id = 1,
                name = "LeighAcosta",
                creationDate = "11 октября 2003",
                moneyNumber = 8818.0f,
                profitPercent = 12.0f,
            ),
            PortfolioItem(
                id = 2,
                name = "LeisaaghAcosta",
                creationDate = "16 декабря 2003",
                moneyNumber = 888.0f,
                profitPercent = -12.0f,
            ),
        )
    }

    override suspend fun createPortfolio(id: Int, name: String) {
        //return
    }

    override suspend fun buyStock(id: Int, number: Int) {
        //return
    }

    override suspend fun sellStock(id: Int, number: Int) {
        //return
    }

    override suspend fun getPortfolio(id: Int): PortfolioItem {
        if (id == 1) return PortfolioItem(1, "Мой портфель", "10 декабря 2023", 1000f, 0.01f)
        else return PortfolioItem(2, "Наш портфель", "10 декабря 2023", 1500f, -0.01f)
    }

    override suspend fun getStocksInPortfolio(id: Int): List<StockItem> {
        return listOf(
            StockItem(
                id = 1,
                name = "Gazprom",
                price = 45.5f,
                stockNumber = 11,
                profitPercent = 6.7f,
                stockId = 1
            ),
            StockItem(
                id = 2,
                name = "Bazprom",
                price = 45.5f,
                stockNumber = 2,
                profitPercent = 6.7f,
                stockId = 2
            )
        )
    }

    override suspend fun getOperationsHistory(): List<OperationItem> {
        return listOf(
            OperationItem(isSale = false, date = "12.12.2023", price = 4294.0),
            OperationItem(isSale = true, date = "11.10.2023", price = 294.0),
            OperationItem(isSale = false, date = "11.08.2023", price = 1234.0),
        )
    }

    override suspend fun getAllStocksInformation(): List<SearchStockItem> {
        return listOf(
            SearchStockItem(
                id = 1,
                name = "Gazprom",
                price = listOf(10.0f, 20.0f),
                country = "Россия",
                companyName = "ООО Газпром"
            ),
            SearchStockItem(
                id = 2,
                name = "Bazprom",
                price = listOf(20.0f, 10.0f),
                country = "Россия",
                companyName = "ООО Базпром"
            )
        )
    }
}