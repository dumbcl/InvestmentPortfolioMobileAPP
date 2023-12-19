package com.example.investmentportfolio.ui.portfolio_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.SearchStockItem
import com.example.investmentportfolio.data.StockItem
import com.example.investmentportfolio.ui.search_screen.elements.SearchStockUIItem
import com.example.investmentportfolio.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockBuyDialog(
    showDropDown: () -> Unit,
    discardDropDown: () -> Unit,
    isDropdownShown: Boolean,
    chooseStockFromMenu: (SearchStockItem) -> Unit,
    lastChosenStock: SearchStockItem?,
    discard: () -> Unit,
    search: (String) -> Unit,
    buy: (String, Int) -> Unit,
    stocks: List<SearchStockItem>
) {
    Box(
        modifier = Modifier
            .width(460.dp)
            .height(500.dp)
            .background(color = AppTheme.colors.supportGreen, shape = RoundedCornerShape(size = 20.dp))
            .border(
                width = 1.dp,
                color = AppTheme.colors.mainGreen,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row() {
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.white,
                        )
                        .border(
                            width = 1.dp,
                            color = AppTheme.colors.supportBrown,
                            shape = RoundedCornerShape(size = 32.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.buy),
                        color = AppTheme.colors.supportBrown,
                        style = AppTheme.typography.regularText
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                //modifier = Modifier,
                text = context.resources.getString(R.string.name),
                color = AppTheme.colors.mainBrown,
                style = AppTheme.typography.regularBoldText
            )
            var stockName by remember { mutableStateOf("") }
            if (lastChosenStock != null) {
                stockName = lastChosenStock.name
            }
            var price by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(295.dp),
                value = stockName,
                onValueChange = { newStockName ->
                    stockName = newStockName
                },
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.white,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                trailingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.clickable(onClick = { search(stockName) })) }
            )
            Text(
                text = "Варианты:",
                modifier = Modifier.clickable( onClick = showDropDown)
            )

            if (isDropdownShown) {
                LazyColumn(
                    modifier = Modifier.height(50.dp)
                ) {
                    items(items = stocks) { stock ->
                        Text(
                            text = stock.name,
                            modifier = Modifier.clickable(onClick = { chooseStockFromMenu(stock) }).align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(21.dp))
            Row() {
                Text(
                    text = context.resources.getString(R.string.stock_price),
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.width(89.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(103.dp),
                    value = lastChosenStock?.price?.toString() ?: "",
                    onValueChange = { newPrice ->
                        price = newPrice
                    },
                    enabled = false,
                    textStyle = AppTheme.typography.smallText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = AppTheme.colors.mainGrey,
                        containerColor = AppTheme.colors.white,
                        unfocusedBorderColor = AppTheme.colors.mainGreen,
                        focusedBorderColor = AppTheme.colors.mainGreen
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            var number by remember { mutableStateOf("") }
            Row(){
                Text(
                    text = context.resources.getString(R.string.number),
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.width(89.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(103.dp),
                    value = number,
                    onValueChange = { newNumber ->
                        number = newNumber
                    },
                    textStyle = AppTheme.typography.smallText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = AppTheme.colors.mainGrey,
                        containerColor = AppTheme.colors.white,
                        unfocusedBorderColor = AppTheme.colors.mainGreen,
                        focusedBorderColor = AppTheme.colors.mainGreen
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(){
                TextButton(
                    onClick = discard,
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.mainGrey,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.cancel),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
                Spacer(modifier = Modifier.width(33.dp))
                TextButton(
                    onClick = { if (number != "" && number.isDigitsOnly() && number.toInt() > 0 && lastChosenStock != null) buy(lastChosenStock.id, number.toInt()) },
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.mainGreen,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.buy),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockSellDialog(
    stockItem: StockItem,
    discard: () -> Unit,
    sell: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(400.dp)
            .background(
                color = AppTheme.colors.supportGreen,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .border(
                width = 1.dp,
                color = AppTheme.colors.mainGreen,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row() {
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.white,
                        )
                        .border(
                            width = 1.dp,
                            color = AppTheme.colors.supportBrown,
                            shape = RoundedCornerShape(size = 32.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.sell),
                        color = AppTheme.colors.supportBrown,
                        style = AppTheme.typography.regularText
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = context.resources.getString(R.string.name),
                color = AppTheme.colors.mainBrown,
                style = AppTheme.typography.regularBoldText
            )
            var stockName by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(295.dp),
                value = stockItem.name,
                onValueChange = { newStockName ->
                    stockName = newStockName
                },
                textStyle = AppTheme.typography.smallText,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = AppTheme.colors.mainGrey,
                    containerColor = AppTheme.colors.white,
                    unfocusedBorderColor = AppTheme.colors.mainGreen,
                    focusedBorderColor = AppTheme.colors.mainGreen
                ),
                enabled = false,
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(21.dp))
            Row(){
                Text(
                    text = context.resources.getString(R.string.stock_price),
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.width(89.dp))
                var price by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier
                        .width(103.dp),
                    value = stockItem.price.toString(),
                    onValueChange = { newPrice ->
                        price = newPrice
                    },
                    enabled = false,
                    textStyle = AppTheme.typography.smallText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = AppTheme.colors.mainGrey,
                        containerColor = AppTheme.colors.white,
                        unfocusedBorderColor = AppTheme.colors.mainGreen,
                        focusedBorderColor = AppTheme.colors.mainGreen
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            var number by remember { mutableStateOf("") }
//            Row(){
//                Text(
//                    text = context.resources.getString(R.string.number),
//                    color = AppTheme.colors.mainBrown,
//                    style = AppTheme.typography.regularBoldText
//                )
//                Spacer(modifier = Modifier.width(89.dp))
//                OutlinedTextField(
//                    modifier = Modifier
//                        .width(103.dp),
//                    value = number,
//                    onValueChange = { newNumber ->
//                        number = newNumber
//                    },
//                    textStyle = AppTheme.typography.smallText,
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        textColor = AppTheme.colors.mainGrey,
//                        containerColor = AppTheme.colors.white,
//                        unfocusedBorderColor = AppTheme.colors.mainGreen,
//                        focusedBorderColor = AppTheme.colors.mainGreen
//                    ),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    singleLine = true,
//                    shape = RoundedCornerShape(8.dp)
//                )
//            }
            Spacer(modifier = Modifier.height(26.dp))
            Row(){
                TextButton(
                    onClick = discard,
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.mainGrey,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.cancel),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
                Spacer(modifier = Modifier.width(33.dp))
                TextButton(
                    onClick = {
                        sell(stockItem.id,)
                    },
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.mainGreen,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.sell),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
            }
        }
    }
}