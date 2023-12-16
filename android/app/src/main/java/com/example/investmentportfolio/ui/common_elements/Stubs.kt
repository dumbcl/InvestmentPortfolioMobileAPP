package com.example.investmentportfolio.ui.common_elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.investmentportfolio.ui.theme.AppTheme

@Composable
fun NetworkStub(
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(40.dp))
    Text(
        text = "Проблемы с интернетом",
        style = AppTheme.typography.smallTitle,
        color = AppTheme.colors.mainPurple
    )
    Text(
        text = "Нажмите на кнопку для перезагрузки",
        style = AppTheme.typography.smallTitle,
        color = AppTheme.colors.mainPurple
    )
    Spacer(modifier = Modifier.height(20.dp))
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            Icons.Default.Refresh,
            "refresh",
            tint = AppTheme.colors.mainPurple,
            modifier = Modifier
                .width(90.dp)
                .height(90.dp),
        )
    }
}

@Composable
fun LoadingStub(){
    CircularProgressIndicator(
        color = AppTheme.colors.mainPurple
    )
}

@Composable
fun SearchStub(){
    Text(
        text = "Ничего не найдено",
        style = AppTheme.typography.smallTitle,
        color = AppTheme.colors.mainPurple
    )
}