package com.example.investmentportfolio.ui.common_elements

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object MyPortfolios : NavigationItem("my_portfolios", R.drawable.icon_home, "Мои портфели")
    object Search : NavigationItem("search", R.drawable.icon_search, "Поиск")
    object History : NavigationItem("history", R.drawable.icon_history, "История")
}

@Composable
fun BottomNavigationBar() {
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
                selected = true,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}