package com.example.investmentportfolio.ui.portfolio_screen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.ui.common_elements.BottomShadow
import com.example.investmentportfolio.ui.theme.AppTheme

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