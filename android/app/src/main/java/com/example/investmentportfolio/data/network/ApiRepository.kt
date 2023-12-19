package com.example.investmentportfolio.data.network

import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRepository {
    @POST("auth/register")
    suspend fun register(@Body user: NWUserLoginRequest): Response<NWUserRegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body user: NWUserLoginRequest): Response<UserLoginResponse>

    @GET("portfolios/all/{userId}")
    suspend fun getUserPortfolios(@Path("userId") userId: String): Response<PortfolioResponse>

    @POST("portfolios")
    suspend fun createPortfolio(@Body portfolio: CreatePortfolioRequest): Response<CreatePortfolioResponce>

    @GET("portfolios/{id}")
    suspend fun getPortfolio(@Path("id") id: Int): Response<SinglePortfolioResponse>

    @POST("purchareds")
    suspend fun buyStock(@Body request: PurchaseStockRequest): Response<PurchasedStockResponseData>

    @DELETE("purchareds/{id}")
    suspend fun sellStock(@Path("id") id: Int): Response<StockInformationResponse>
    //suspend fun getPortfolio(id: Int): PortfolioItem
    suspend fun getStocksInPortfolio(id: Int): List<StockItem>
    suspend fun getOperationsHistory(): List<OperationItem>

    @GET("stocks")
    suspend fun getAllStocksInformation(): Response<List<StockInformationResponse>>
}