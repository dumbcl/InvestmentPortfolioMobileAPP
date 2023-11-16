package com.example.investmentportfolio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    mainGreen: Color,
    white: Color,
    supportGreen: Color,
    black: Color,
    mainGrey: Color,
    supportGrey: Color,
    mainBrown: Color,
    supportBrown: Color,
    mainPurple: Color,
    brightGreen: Color,
    brightRed: Color,
    deepBlue: Color,
){
    var mainGreen by mutableStateOf(mainGreen)
        private set
    var white by mutableStateOf(white)
        private set
    var supportGreen by mutableStateOf(supportGreen)
        private set
    var black by mutableStateOf(black)
        private set
    var mainGrey by mutableStateOf(mainGrey)
        private set
    var supportGrey by mutableStateOf(supportGrey)
        private set
    var mainBrown by mutableStateOf(mainBrown)
        private set
    var supportBrown by mutableStateOf(supportBrown)
        private set
    var mainPurple by mutableStateOf(mainPurple)
        private set
    var brightGreen by mutableStateOf(brightGreen)
        private set
    var brightRed by mutableStateOf(brightRed)
        private set
    var deepBlue by mutableStateOf(deepBlue)
        private set

    fun update(other: AppColors){
        mainGreen = other.mainGreen
        white = other.white
        supportGreen = other.supportGreen
        black = other.black
        mainGrey = other.mainGrey
        supportGrey = other.supportGrey
        mainBrown = other.mainBrown
        supportBrown = other.supportBrown
        mainPurple = other.mainPurple
        brightGreen = other.brightGreen
        brightRed = other.brightRed
        deepBlue = other.deepBlue
    }
}

private val ColorScheme = AppColors(
    mainGreen = Palette.MainGreen,
    white = Palette.White,
    supportGreen = Palette.SupportGreen,
    black = Palette.Black,
    mainGrey = Palette.MainGrey,
    supportGrey = Palette.SupportGrey,
    mainBrown = Palette.MainBrown,
    supportBrown = Palette.SupportBrown,
    mainPurple = Palette.MainPurple,
    brightGreen = Palette.BrightGreen,
    brightRed = Palette.BrightRed,
    deepBlue = Palette.DeepBlue
)

internal val LocalCustomColors = staticCompositionLocalOf<AppColors> { error("No colors provided") }

@Composable
fun ProvideAppTheme(
    colors: AppColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalCustomColors provides colorPalette, content = content)
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalCustomColors.current
    val typography: Typography
        get() = Typography

}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    ProvideAppTheme(colors = colorScheme) {
        MaterialTheme(
            content = content
        )
    }
}