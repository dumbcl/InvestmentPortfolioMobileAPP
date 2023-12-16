package com.example.investmentportfolio.data.network

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.SearchStockItem

interface ApiRepository {
    suspend fun getOperationsHistory(): List<OperationItem>
    suspend fun getAllStocksInformation(): List<SearchStockItem>
}