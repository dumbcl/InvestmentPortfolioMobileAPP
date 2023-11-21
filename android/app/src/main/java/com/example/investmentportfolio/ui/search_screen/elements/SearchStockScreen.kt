package com.example.investmentportfolio.ui.search_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.theme.AppTheme

@Preview
@Composable
fun PreviewSearchStockScreen() {
    AppTheme {
        SearchStockScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchStockScreen() {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar() },
        content = {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(13.dp))
                Text(
                    text = context.resources.getString(R.string.search_stock),
                    color = AppTheme.colors.mainGrey,
                    style = AppTheme.typography.bigTitle,
                )
                Spacer(modifier = Modifier.height(26.dp))
                var stockName by remember { mutableStateOf("") }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = stockName,
                    onValueChange = { newStockName ->
                        stockName = newStockName
                    },
                    placeholder = {
                        Text(
                            context.resources.getString(R.string.search),
                            color = AppTheme.colors.supportGrey
                        )
                    },
                    textStyle = AppTheme.typography.smallText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = AppTheme.colors.supportGrey,
                        containerColor = AppTheme.colors.supportGreen,
                        unfocusedBorderColor = AppTheme.colors.mainGreen,
                        focusedBorderColor = AppTheme.colors.mainGreen
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )
            }
        }
    )
}

