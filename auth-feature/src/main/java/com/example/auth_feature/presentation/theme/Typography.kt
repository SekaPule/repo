package com.example.auth_feature.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.auth_feature.R

data class Typography(
    val textStyle2: TextStyle,
    val textStyle17: TextStyle
)

val OfficinaFont = FontFamily(Font(R.font.officina_sans))

val customTypography = Typography(
    textStyle2 = TextStyle(
        fontFamily = OfficinaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp
    ),
    textStyle17 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    )
)