package com.example.investmentportfolio.ui.portfolio_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.PortfolioItem
import com.example.investmentportfolio.data.StockItem
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.common_elements.NavigationItem
import com.example.investmentportfolio.ui.common_elements.SuccessDialog
import com.example.investmentportfolio.ui.portfolio_screen.PortfolioFragmentDirections
import com.example.investmentportfolio.ui.portfolio_screen.PortfolioScreenState
import com.example.investmentportfolio.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PortfolioScreen(
    uiState: PortfolioScreenState,
    navController: NavController,
    showBuyDialog: () -> Unit,
    showSellDialog: (StockItem) -> Unit,
    discardBuyDialog: () -> Unit,
    discardSellDialog: () -> Unit,
    buyStock: (Int, Int) -> Unit,
    sellStock: (Int, Int) -> Unit,
    searchStock: (String) -> Unit,
    showDropdown: () -> Unit,
    discardDropdown: () -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar(NavigationItem.MyPortfolios, navController, fromAnotherFragment = true) },
        content = {
            Column {
                Row {
                    IconButton(
                        onClick = { navController.navigate(PortfolioFragmentDirections.actionPortfolioFragmentToMyPortfoliosFragment()) },
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
                }
                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = uiState.portfolio.name,
                    color = AppTheme.colors.mainGrey,
                    style = AppTheme.typography.bigTitle,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = uiState.portfolio.moneyNumber.toString(),
                        color = AppTheme.colors.mainGreen,
                        style = AppTheme.typography.largeBoldTitle,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = (uiState.portfolio.profitPercent * uiState.portfolio.moneyNumber).toString(),
                        color = AppTheme.colors.supportGrey,
                        style = AppTheme.typography.mediumTitle,
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 0.dp, 0.dp)
                            .weight(1f)
                    )
                    Text(
                        text = "(${uiState.portfolio.profitPercent}%)",
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
                }
                Spacer(modifier = Modifier.height(29.dp))
                LazyColumn {
                    items(items = uiState.stocks) { item ->
                        StockUIItem(
                            item = item,
                            name = item.name,
                            price = item.price,
                            stockNumber = item.stockNumber,
                            profitPercent = item.profitPercent,
                            sell = showSellDialog
                        )
                    }
                }
            }
        }
    )

    if (uiState.isSuccessSellDialogShown) {
        Dialog(
            onDismissRequest = {},
            content = { SuccessDialog(message = "Акция успешно проданы") }
        )
    }

    if (uiState.isSuccessBuyDialogShown) {
        Dialog(
            onDismissRequest = {},
            content = { SuccessDialog(message = "Акция успешно добавлена") }
        )
    }

    if (uiState.isSellDialogShown) {
        Dialog(
            onDismissRequest = discardSellDialog,
            content = { StockSellDialog(stockItem = uiState.clickedStockToSell!!) }
        )
    }
}