package com.example.investmentportfolio.data.network

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.SearchStockItem

class ApiRepositoryImpl: ApiRepository {
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