package com.example.investmentportfolio.ui.history_screen.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

@Composable
fun OperationUIItem(isSale: Boolean, date: String, price: Double) {
    val context = LocalContext.current
    var icon = painterResource(R.drawable.icon_purchase_operation)
    var itemTitle = context.resources.getString(R.string.purchase)
    if (isSale) {
        icon = painterResource(R.drawable.icon_sale_operation)
        itemTitle = context.resources.getString(R.string.sale)
    }
    Row() {
        Icon(
            icon,
            "sale or purchase",
            tint = Color.Unspecified,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.width(220.dp)) {
            Text(
                text = itemTitle,
                color = AppTheme.colors.mainBrown,
                style = AppTheme.typography.regularBoldText
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = date,
                color = AppTheme.colors.mainPurple,
                style = AppTheme.typography.regularText
            )
        }
        Text(
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            text = if (price > 0) "+$price Р" else "$price Р",
            color = AppTheme.colors.mainBrown,
            style = AppTheme.typography.regularBoldText
        )
    }
}