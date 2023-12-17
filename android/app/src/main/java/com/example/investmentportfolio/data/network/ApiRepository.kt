package com.example.investmentportfolio.data.network

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem

interface ApiRepository {
    suspend fun getUserPortfolios(): List<PortfolioItem>
    suspend fun createPortfolio(id: Int, name: String)
    suspend fun buyStock(id: Int, number: Int)
    suspend fun sellStock(id: Int, number: Int)
    suspend fun getPortfolio(id: Int): PortfolioItem
    suspend fun getStocksInPortfolio(id: Int): List<StockItem>
    suspend fun getOperationsHistory(): List<OperationItem>
    suspend fun getAllStocksInformation(): List<SearchStockItem>
}