package com.example.investmentportfolio.ui.history_screen.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.investmentportfolio.R
import com.example.investmentportfolio.data.OperationItem
import com.example.investmentportfolio.ui.common_elements.BottomNavigationBar
import com.example.investmentportfolio.ui.common_elements.LoadingStub
import com.example.investmentportfolio.ui.common_elements.NavigationItem
import com.example.investmentportfolio.ui.common_elements.NetworkStub
import com.example.investmentportfolio.ui.history_screen.OperationsHistoryScreenState
import com.example.investmentportfolio.ui.theme.AppTheme

@Preview
@Composable
fun PreviewOperationsHistoryScreen() {
    AppTheme {
        OperationsHistoryScreen(
            OperationsHistoryScreenState(false, false, listOf()),
            NavController(LocalContext.current),
            {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OperationsHistoryScreen(
    uiState: OperationsHistoryScreenState,
    navController: NavController,
    reload: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = { BottomNavigationBar(NavigationItem.History, navController) },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = context.resources.getString(R.string.operations_history),
                    color = AppTheme.colors.mainGrey,
                    style = AppTheme.typography.bigTitle,
                    modifier = Modifier.padding(10.dp, 23.dp, 0.dp, 0.dp).align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(20.dp))

                if (uiState.isLoading) {
                    LoadingStub()
                } else if (uiState.isError) {
                    NetworkStub(onClick = reload)
                } else {
                    LazyColumn(
                        modifier = Modifier.align(Alignment.Start),
                        verticalArrangement = Arrangement.spacedBy(space = 17.dp),
                        contentPadding = PaddingValues(10.dp),
                        //horizontalAlignment = Alignment.Start
                    ) {
                        items(items = uiState.operations) { item ->
                            OperationUIItem(
                                isSale = item.isSale,
                                date = item.date,
                                price = item.price,
                            )
                        }
                    }
                }
            }
        }
    )


}