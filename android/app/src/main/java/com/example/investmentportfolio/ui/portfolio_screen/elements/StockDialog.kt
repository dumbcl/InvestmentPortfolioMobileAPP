package com.example.investmentportfolio.ui.portfolio_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.StockItem
import com.example.investmentportfolio.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockBuyDialog() {
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(400.dp)
            .background(color = AppTheme.colors.supportGreen)
            .border(width = 1.dp, color = AppTheme.colors.mainGreen, shape = RoundedCornerShape(size = 20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Column(){
            Row(){
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.white,
                        )
                        .border(width = 1.dp, color = AppTheme.colors.supportBrown, shape = RoundedCornerShape(size = 32.dp))
                ) {
                    Text(
                        text = context.resources.getString(R.string.buy),
                        color = AppTheme.colors.supportBrown,
                        style = AppTheme.typography.regularText
                    )
                }
                Spacer(modifier = Modifier.width(33.dp))
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.supportGreen,
                        )
                        .border(width = 1.dp, color = AppTheme.colors.supportBrown, shape = RoundedCornerShape(size = 32.dp))
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
                    value = price,
                    onValueChange = { newPrice ->
                        price = newPrice
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
            Spacer(modifier = Modifier.height(15.dp))
            Row(){
                Text(
                    text = context.resources.getString(R.string.number),
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.width(89.dp))
                var number by remember { mutableStateOf("") }
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
                    onClick = {},
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
                    onClick = {},
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
fun StockSellDialog(stockItem: StockItem) {
    Box(
        modifier = Modifier
            .width(360.dp)
            .height(400.dp)
            .background(color = AppTheme.colors.supportGreen)
            .border(width = 1.dp, color = AppTheme.colors.mainGreen, shape = RoundedCornerShape(size = 20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Column(){
            Row(){
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.white,
                        )
                        .border(width = 1.dp, color = AppTheme.colors.supportBrown, shape = RoundedCornerShape(size = 32.dp))
                ) {
                    Text(
                        text = context.resources.getString(R.string.buy),
                        color = AppTheme.colors.supportBrown,
                        style = AppTheme.typography.regularText
                    )
                }
                Spacer(modifier = Modifier.width(33.dp))
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(35.dp)
                        .background(
                            color = AppTheme.colors.supportGreen,
                        )
                        .border(width = 1.dp, color = AppTheme.colors.supportBrown, shape = RoundedCornerShape(size = 32.dp))
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
                    value = price,
                    onValueChange = { newPrice ->
                        price = newPrice
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
            Spacer(modifier = Modifier.height(15.dp))
            Row(){
                Text(
                    text = context.resources.getString(R.string.number),
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.width(89.dp))
                var number by remember { mutableStateOf("") }
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
                    onClick = {},
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
                    onClick = {},
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