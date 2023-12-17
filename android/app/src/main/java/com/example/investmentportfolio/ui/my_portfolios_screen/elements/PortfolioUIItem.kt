package com.example.investmentportfolio.ui.my_portfolios_screen.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.ui.common_elements.BottomShadow
import com.example.investmentportfolio.ui.theme.AppTheme

@Composable
fun PortfolioUIItem(id: Int, name: String, creationDate: String, moneyNumber: Float, profitPercent: Float, onClick: (Int) -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = { onClick(id) })
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
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
                    text = creationDate,
                    color = AppTheme.colors.mainPurple,
                    style = AppTheme.typography.regularText
                )
            }
            Spacer(modifier = Modifier.width(60.dp))
            Column(
                Modifier.width(130.dp)
            ) {
                Text(
                    text = "$profitPercent %",
                    color = AppTheme.colors.mainBrown,
                    style = AppTheme.typography.regularBoldText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$moneyNumber Р",
                    color = AppTheme.colors.mainPurple,
                    style = AppTheme.typography.regularBoldText
                )
            }
        }
        BottomShadow()
    }
}