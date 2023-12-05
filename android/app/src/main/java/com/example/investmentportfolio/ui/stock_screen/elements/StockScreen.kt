package com.example.investmentportfolio.ui.stock_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.common_elements.NavigationItem
import com.example.investmentportfolio.ui.stock_screen.StockFragmentDirections
import com.example.investmentportfolio.ui.theme.AppTheme
import com.example.investmentportfolio.ui.theme.Palette

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StockScreen(navController: NavController){
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar(NavigationItem.Search, navController, fromAnotherFragment = true) },
        content = {
            Column {
                Row {
                    IconButton(
                        onClick = { navController.navigate(StockFragmentDirections.actionStockFragmentToSearchStocksFragment()) },
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
                        text = "Газпром",
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
                Spacer(modifier = Modifier.height(40.dp))
                PriceChart()
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Описание",
                    color = AppTheme.colors.mainGreen,
                    style = AppTheme.typography.someTitle,
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    Column(){
                        Text(
                            text = "Стоимость",
                            color = AppTheme.colors.supportGrey,
                            style = AppTheme.typography.regularText,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "71.42 Р",
                            color = AppTheme.colors.mainGreen,
                            style = AppTheme.typography.regularText,
                        )
                    }
                    Spacer(modifier = Modifier.width(60.dp))
                    Column(){
                        Text(
                            text = "Стоимость",
                            color = AppTheme.colors.supportGrey,
                            style = AppTheme.typography.regularText,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "71.42 Р",
                            color = AppTheme.colors.mainGreen,
                            style = AppTheme.typography.regularText,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth()
                ){
                    Column(){
                        Text(
                            text = "Стоимость",
                            color = AppTheme.colors.supportGrey,
                            style = AppTheme.typography.regularText,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "71.42 Р",
                            color = AppTheme.colors.mainGreen,
                            style = AppTheme.typography.regularText,
                        )
                    }
                    Spacer(modifier = Modifier.width(60.dp))
                    Column(){
                        Text(
                            text = "Стоимость",
                            color = AppTheme.colors.supportGrey,
                            style = AppTheme.typography.regularText,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "71.42 Р",
                            color = AppTheme.colors.mainGreen,
                            style = AppTheme.typography.regularText,
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun PriceChart() {
    val data = listOf(25f, 45f, 32f, 50f, 23f)

    Canvas(modifier = Modifier.width(300.dp).height(150.dp).padding(20.dp, 0.dp), onDraw = {
        drawLineChart(data)
    })
}

private fun DrawScope.drawLineChart(data: List<Float>) {
    if (data.isEmpty()) return

    val maxValue = data.maxOrNull() ?: 0f
    val chartWidth = size.width
    val chartHeight = size.height
    val stepX = chartWidth / (data.size - 1)
    val stepY = chartHeight / (maxValue + 10)

    for (i in 0 until data.size - 1) {
        drawLine(
            color = Color(0xFF3FBFA0),
            strokeWidth = 4.dp.toPx(),
            cap = StrokeCap.Round,
            start = androidx.compose.ui.geometry.Offset(stepX * i, chartHeight - data[i] * stepY),
            end = androidx.compose.ui.geometry.Offset(stepX * (i + 1), chartHeight - data[i + 1] * stepY)
        )
    }
}
