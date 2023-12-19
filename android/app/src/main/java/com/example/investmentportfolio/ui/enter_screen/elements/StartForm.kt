package com.example.investmentportfolio.ui.enter_screen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

@Preview
@Composable
fun PreviewStartForm() {
    AppTheme {
        StartForm( {}, {} )
    }
}

@Composable
fun StartForm(
    toLoginForm: () -> Unit,
    toRegistratonForm: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(color = AppTheme.colors.white),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextButton(
                onClick = toLoginForm,
                modifier = Modifier
                    .width(188.dp)
                    .height(49.dp)
                    .background(
                        color = AppTheme.colors.mainGreen,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                val context = LocalContext.current
                Text(
                    text = context.resources.getString(R.string.enter),
                    color = AppTheme.colors.white,
                    style = AppTheme.typography.smallText
                )
            }
            TextButton(
                onClick = toRegistratonForm,
                modifier = Modifier
                    .width(188.dp)
                    .height(49.dp)
                    .background(
                        color = AppTheme.colors.mainGreen,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                val context = LocalContext.current
                Text(
                    text = context.resources.getString(R.string.register),
                    color = AppTheme.colors.white,
                    style = AppTheme.typography.smallText
                )
            }
        }
    }
}