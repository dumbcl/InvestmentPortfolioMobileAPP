package com.example.investmentportfolio.ui.my_portfolios_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.common_elements.BottomShadow
import com.example.investmentportfolio.ui.common_elements.NavigationItem
import com.example.investmentportfolio.ui.my_portfolios_screen.MyPortfoliosFragmentDirections
import com.example.investmentportfolio.ui.theme.AppTheme

val mockedPortfolios = listOf(
    PortfolioItem("1", "Hello", "11 октября, 2023", 1223, 12),
    PortfolioItem("1", "Hello", "11 октября, 2023", 1223, 12),
    PortfolioItem("1", "Hello", "11 октября, 2023", 1223, 12)
)

@Preview
@Composable
fun PreviewMyPortfoliosScreen() {
    AppTheme {
        MyPortfoliosScreen(NavController(LocalContext.current))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPortfoliosScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar(NavigationItem.MyPortfolios, navController) },
        content = {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(8.dp, 23.dp, 10.dp, 0.dp)
                ) {
                    Text(
                        text = "Hi, Name",
                        color = AppTheme.colors.mainBrown,
                        style = AppTheme.typography.largeTitle,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clickable { navController.navigate(MyPortfoliosFragmentDirections.actionMyPortfoliosFragmentToPortfolioFragment()) }
                    )
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .width(188.dp)
                            .height(49.dp)
                            .background(
                                color = AppTheme.colors.mainGreen,
                                shape = RoundedCornerShape(size = 8.dp)
                            )
                            .align(Alignment.BottomEnd)
                    ) {
                        Text(
                            text = context.resources.getString(R.string.create_portfolio),
                            color = AppTheme.colors.white,
                            style = AppTheme.typography.smallText,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(49.dp))
                LazyColumn {
                    items(items = mockedPortfolios) { item ->
                        PortfolioUIItem(
                            name = item.name,
                            creationDate = item.creationDate,
                            moneyNumber = item.moneyNumber,
                            profitPercent = item.profitPercent
                        )
                    }
                }
            }
        }
    )
}