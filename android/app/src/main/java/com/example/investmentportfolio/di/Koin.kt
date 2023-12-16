package com.example.investmentportfolio.di

import com.example.investmentportfolio.data.PortfolioRepository
import com.example.investmentportfolio.data.PortfolioRepositoryImpl
import com.example.investmentportfolio.data.network.ApiRepository
import com.example.investmentportfolio.data.network.ApiRepositoryImpl
import com.example.investmentportfolio.ui.history_screen.OperationsHistoryViewModel
import com.example.investmentportfolio.ui.search_screen.SearchStocksViewModel
import com.example.investmentportfolio.ui.stock_screen.StockViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

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
}

val repositoryModule = module {
    single<ApiRepository> { ApiRepositoryImpl(
        //httpClient = get(),
        //apiCharacterMapper = get(),
        //apiEpisodeMapper = get(),
        //apiLocationMapper = get()
    ) }
    single<PortfolioRepository> { PortfolioRepositoryImpl(
        apiRepository = get()
    ) }
}