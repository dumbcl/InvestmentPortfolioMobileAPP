package com.example.investmentportfolio.ui.common_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.investmentportfolio.R
import com.example.investmentportfolio.ui.theme.AppTheme

@Composable
fun SuccessDialog(message: String) {
    Box(
        modifier = Modifier
            .width(363.dp)
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
                text = message,
                color = AppTheme.colors.mainGrey,
                style = AppTheme.typography.regularText,
            )
            Spacer(modifier = Modifier.height(26.dp))
            Icon(
                Icons.Default.CheckCircle,
                "success",
                tint = AppTheme.colors.mainGreen,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
            )
        }
    }
}