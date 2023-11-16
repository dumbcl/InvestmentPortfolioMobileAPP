package com.example.investmentportfolio.ui.my_portfolios_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePortfolioDialog() {
    Box(
        modifier = Modifier
            .width(359.dp)
            .height(182.dp)
            .background(color = AppTheme.colors.supportGreen)
            .border(width = 1.dp, color = AppTheme.colors.mainGreen, shape = RoundedCornerShape(size = 20.dp)),
    contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            Text(
                text = context.resources.getString(R.string.enter_portfolio_name),
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.smallText,
            )
            Spacer(modifier = Modifier.height(10.dp))
            var portfolioName by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier
                    .width(306.dp),
                value = portfolioName,
                onValueChange = { newPortfolioName ->
                    portfolioName = newPortfolioName
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
            Spacer(modifier = Modifier.height(25.dp))
            Row() {
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(32.dp)
                        .background(
                            color = AppTheme.colors.mainBrown,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.cancel),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
                Spacer(modifier = Modifier.width(17.dp))
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .width(131.dp)
                        .height(32.dp)
                        .background(
                            color = AppTheme.colors.mainGreen,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                ) {
                    Text(
                        text = context.resources.getString(R.string.save),
                        color = AppTheme.colors.white,
                        style = AppTheme.typography.smallText
                    )
                }
            }
        }
    }
}