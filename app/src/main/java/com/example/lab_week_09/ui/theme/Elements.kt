package com.example.lab_week_09.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Title with default color from theme
@Composable
fun OnBackgroundTitleText(text: String) {
    TitleText(text = text, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun TitleText(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = color
    )
}

// Item text
@Composable
fun OnBackgroundItemText(text: String) {
    ItemText(text = text, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun ItemText(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = color
    )
}

// Main Button
@Composable
fun PrimaryTextButton(text: String, onClick: () -> Unit) {
    TextButtonCustom(
        text = text,
        textColor = Color.White,
        onClick = onClick
    )
}

// Custom button component
@Composable
fun TextButtonCustom(text: String, textColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
