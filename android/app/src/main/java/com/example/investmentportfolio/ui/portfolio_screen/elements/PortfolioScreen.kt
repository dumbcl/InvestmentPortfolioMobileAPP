package com.example.investmentportfolio.ui.portfolio_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.StockItem
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.StockDialog
import com.example.investmentportfolio.ui.theme.AppTheme

val mockedStocks = listOf(
    StockItem("1", "Газпром", 1000, 10, 12),
    StockItem("1", "Газпром", 1000, 10, 12),
    StockItem("1", "Газпром", 1000, 10, 12),
)

@Preview
@Composable
fun PreviewPortfolioScreen() {
    AppTheme {
        PortfolioScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PortfolioScreen() {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        content = {
            Column {
                Row {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "go back",
                            tint = AppTheme.colors.deepBlue,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    Text(
                        text = "Мой портфель",
                        color = AppTheme.colors.mainGreen,
                        style = AppTheme.typography.someTitle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(0.dp, 10.dp),
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Мой портфель",
                    color = AppTheme.colors.mainGrey,
                    style = AppTheme.typography.bigTitle,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(11.dp))
                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "7000",
                        color = AppTheme.colors.mainGreen,
                        style = AppTheme.typography.largeBoldTitle,
                        modifier = Modifier.weight(3f)
                    )
                    Text(
                        text = "+200",
                        color = AppTheme.colors.supportGrey,
                        style = AppTheme.typography.mediumTitle,
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                            .weight(1f)
                    )
                    Text(
                        text = "(0.1%)",
                        color = AppTheme.colors.mainGreen,
                        style = AppTheme.typography.mediumTitle,
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                            .weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .background(
                                color = AppTheme.colors.mainGreen,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                    ) {
                        Text(
                            text = context.resources.getString(R.string.invest),
                            color = AppTheme.colors.white,
                            style = AppTheme.typography.smallText
                        )
                    }
                }
                Spacer(modifier = Modifier.height(23.dp))
                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(35.dp)
                            .background(
                                color = AppTheme.colors.white,
                            )
                            .border(
                                width = 1.dp,
                                color = AppTheme.colors.mainGreen,
                                shape = RoundedCornerShape(size = 32.dp)
                            )
                    ) {
                        Text(
                            text = context.resources.getString(R.string.stocks),
                            color = AppTheme.colors.mainGreen,
                            style = AppTheme.typography.regularText
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .weight(1f)
                            .height(35.dp)
                            .background(
                                color = AppTheme.colors.white,
                            )
                            .border(
                                width = 1.dp,
                                color = AppTheme.colors.supportGrey,
                                shape = RoundedCornerShape(size = 32.dp)
                            )
                    ) {
                        Text(
                            text = context.resources.getString(R.string.currencies),
                            color = AppTheme.colors.supportGrey,
                            style = AppTheme.typography.regularText
                        )
                    }
                }
                Spacer(modifier = Modifier.height(29.dp))
                LazyColumn {
                    items(items = mockedStocks) { item ->
                        StockUIItem(
                            name = item.name,
                            price = item.price,
                            stockNumber = item.stockNumber,
                            profitPercent = item.profitPercent
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun StockUIItem(name: String, price: Int, stockNumber: Int, profitPercent: Int) {
    Column {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                Modifier.width(230.dp)
            ) {
                Text(
                    text = name,
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$price Р",
                    color = AppTheme.colors.mainPurple,
                    style = AppTheme.typography.regularText
                )
            }
            Spacer(modifier = Modifier.width(60.dp))
            Column(
                Modifier.width(130.dp)
            ) {
                Text(
                    text = "$stockNumber акций",
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$profitPercent %",
                    color = AppTheme.colors.mainPurple,
                    style = AppTheme.typography.regularBoldText
                )
            }
        }
        BottomShadow()
    }
}

@Composable
fun BottomShadow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppTheme.colors.mainGreen.copy(alpha = 0.2f),
                        Color.Transparent,
                    )
                )
            )
    )
}