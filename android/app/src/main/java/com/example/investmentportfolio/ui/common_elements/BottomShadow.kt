package com.example.investmentportfolio.ui.common_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.ui.theme.AppTheme

@Composable
fun BottomShadow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppTheme.colors.mainGreen.copy(alpha = 0.2f),
                        Color.Transparent,
                    )
                )
            )
    )
}