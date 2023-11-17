package com.example.investmentportfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.investmentportfolio.ui.common_elements.SuccessDialog
import com.example.investmentportfolio.ui.enter_screen.elements.EnterForm
import com.example.investmentportfolio.ui.enter_screen.elements.RegistrationForm
import com.example.investmentportfolio.ui.enter_screen.elements.StartForm
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.CreatePortfolioDialog
import com.example.investmentportfolio.ui.my_portfolios_screen.elements.StockDialog
import com.example.investmentportfolio.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StockDialog(true)
                }
            }
        }
    }
}