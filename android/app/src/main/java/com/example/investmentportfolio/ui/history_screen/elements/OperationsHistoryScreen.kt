package com.example.investmentportfolio.ui.history_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.enter_screen.elements.RegistrationForm
import com.example.investmentportfolio.ui.theme.AppTheme

val mockedOperations = listOf(
    OperationItem(true, "11 октября, 2023", 1223),
    OperationItem(false, "11 октября, 2023", -1223),
    OperationItem(true, "11 октября, 2023", 12),
    OperationItem(true, "11 октября, 2023", 1223),
    OperationItem(false, "11 октября, 2023", -1223),
    OperationItem(true, "11 октября, 2023", 12), OperationItem(true, "11 октября, 2023", 1223),
    OperationItem(false, "11 октября, 2023", -1223),
)

@Preview
@Composable
fun PreviewOperationsHistoryScreen() {
    AppTheme {
        OperationsHistoryScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OperationsHistoryScreen() {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        content = {
            Column {
                Text(
                    text = context.resources.getString(R.string.operations_history),
                    color = AppTheme.colors.mainGrey,
                    style = AppTheme.typography.bigTitle,
                    modifier = Modifier.padding(8.dp, 23.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(space = 17.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {

                    items(items = mockedOperations) { item ->
                        OperationUIItem(
                            isSale = item.isSale,
                            date = item.date,
                            price = item.price,
                        )
                    }
                }
            }
        }
    )


}

@Composable
fun OperationUIItem(isSale: Boolean, date: String, price: Int) {
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
        //Spacer(modifier = Modifier.width(60.dp))
        Text(
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            text = if (price > 0) "+$price Р" else "$price Р",
            color = AppTheme.colors.mainBrown,
            style = AppTheme.typography.regularBoldText
        )
    }
}