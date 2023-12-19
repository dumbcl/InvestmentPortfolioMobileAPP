package com.example.investmentportfolio.di

import android.content.Context
import android.content.SharedPreferences
import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.PortfolioRepositoryImpl
import com.example.investmentportfolio.data.network.ApiRepository
import com.example.investmentportfolio.data.network.AuthInterceptor
import com.example.investmentportfolio.ui.enter_screen.EnterViewModel
import com.example.investmentportfolio.ui.history_screen.OperationsHistoryViewModel
import com.example.investmentportfolio.ui.my_portfolios_screen.MyPortfoliosViewModel
import com.example.investmentportfolio.ui.portfolio_screen.PortfolioViewModel
import com.example.investmentportfolio.ui.search_screen.SearchStocksViewModel
import com.example.investmentportfolio.ui.stock_screen.StockViewModel
import com.example.investmentportfolio.util.API_ENDPOINT
import com.example.investmentportfolio.util.CONNECT_TIMEOUT
import com.example.investmentportfolio.util.READ_TIMEOUT
import com.example.investmentportfolio.util.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelsModule,
            repositoryModule,
        )
    }

val viewModelsModule = module {
    viewModel { OperationsHistoryViewModel(repository = get()) }
    viewModel { SearchStocksViewModel(repository = get()) }
    viewModel { StockViewModel(repository = get()) }
    viewModel { MyPortfoliosViewModel(repository = get()) }
    viewModel { PortfolioViewModel(repository = get()) }
    viewModel { EnterViewModel(repository = get()) }
}

val repositoryModule = module {
//    single<ApiRepository> { ApiRepositoryImpl(
//        //httpClient = get(),
//        //apiCharacterMapper = get(),
//        //apiEpisodeMapper = get(),
//        //apiLocationMapper = get()
//    ) }

    single { provideSharedPreferences(androidContext()) }
    single { provideAuthInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }


    single<PortfolioRepository> { PortfolioRepositoryImpl(
        apiRepository = get(),
        sharedPreferences = get()
    ) }
}


private fun provideSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences("APP_SHARED_PREFERENCES", Context.MODE_PRIVATE)
}

private fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor {
    return AuthInterceptor(sharedPreferences)
}

private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): ApiRepository {
    return retrofit.create(ApiRepository::class.java)
}
