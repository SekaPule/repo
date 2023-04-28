package com.example.repo.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun RepoTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        values = arrayOf(
            LocalColorProvider provides lightPalette,
            LocalTypographyProvider provides customTypography
        ),
        content = content
    )
}

object AppTheme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypographyProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors>{
    error("No default colors provided")
}
val LocalTypographyProvider = staticCompositionLocalOf<Typography> {
    error("No default typography provided")
}