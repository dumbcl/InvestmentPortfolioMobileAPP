package com.example.investmentportfolio.ui.common_elements

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.history_screen.OperationsHistoryFragmentDirections
import com.example.investmentportfolio.ui.my_portfolios_screen.MyPortfoliosFragmentDirections
import com.example.investmentportfolio.ui.portfolio_screen.PortfolioFragmentDirections
import com.example.investmentportfolio.ui.search_screen.SearchStocksFragmentDirections
import com.example.investmentportfolio.ui.stock_screen.StockFragmentDirections
import com.example.investmentportfolio.ui.theme.AppTheme

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object MyPortfolios : NavigationItem("my_portfolios", R.drawable.icon_home, "Мои портфели")
    object Search : NavigationItem("search", R.drawable.icon_search, "Поиск")
    object History : NavigationItem("history", R.drawable.icon_history, "История")
}

@Composable
fun BottomNavigationBar(
    selectedItem: NavigationItem,
    navController: NavController,
    fromAnotherFragment: Boolean = false
) {
    val items = listOf(
        NavigationItem.MyPortfolios,
        NavigationItem.Search,
        NavigationItem.History,
    )
    BottomNavigation(
        backgroundColor = AppTheme.colors.mainGreen,
        contentColor = AppTheme.colors.white
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = AppTheme.colors.white,
                unselectedContentColor = AppTheme.colors.white.copy(0.4f),
                alwaysShowLabel = true,
                selected = selectedItem == item,
                onClick = {
                    navigateByNavigationBar(
                        selectedItem,
                        item,
                        navController,
                        fromAnotherFragment
                    )
                }
            )
        }
    }
}

fun navigateByNavigationBar(
    selectedItem: NavigationItem,
    clickedItem: NavigationItem,
    navController: NavController,
    fromAnotherFragment: Boolean
) {
    return when (selectedItem) {
        NavigationItem.MyPortfolios -> {
            when (clickedItem) {
                NavigationItem.History -> {
                    if (fromAnotherFragment) navController.navigate(PortfolioFragmentDirections.actionPortfolioFragmentToOperationsHistoryFragment())
                    else navController.navigate(MyPortfoliosFragmentDirections.actionMyPortfoliosFragmentToOperationsHistoryFragment())
                }

                NavigationItem.Search -> {
                    if (fromAnotherFragment) navController.navigate(PortfolioFragmentDirections.actionPortfolioFragmentToSearchStocksFragment())
                    else navController.navigate(MyPortfoliosFragmentDirections.actionMyPortfoliosFragmentToSearchStocksFragment())
                }

                else -> {}
            }
        }

        NavigationItem.Search -> {
            when (clickedItem) {
                NavigationItem.History -> {
                    if (fromAnotherFragment) navController.navigate(StockFragmentDirections.actionStockFragmentToOperationsHistoryFragment())
                    else navController.navigate(SearchStocksFragmentDirections.actionSearchStocksFragmentToOperationsHistoryFragment())
                }

                NavigationItem.MyPortfolios -> {
                    if (fromAnotherFragment) navController.navigate(StockFragmentDirections.actionStockFragmentToMyPortfoliosFragment())
                    else navController.navigate(SearchStocksFragmentDirections.actionSearchStocksFragmentToMyPortfoliosFragment())
                }

                else -> {}
            }
        }

        NavigationItem.History -> {
            when (clickedItem) {
                NavigationItem.MyPortfolios -> navController.navigate(
                    OperationsHistoryFragmentDirections.actionOperationsHistoryFragmentToMyPortfoliosFragment()
                )

                NavigationItem.Search -> navController.navigate(OperationsHistoryFragmentDirections.actionOperationsHistoryFragmentToSearchStocksFragment())
                else -> {}
            }
        }
    }
}