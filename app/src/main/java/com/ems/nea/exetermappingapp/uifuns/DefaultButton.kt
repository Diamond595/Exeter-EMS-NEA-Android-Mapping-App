package com.ems.nea.exetermappingapp.uifuns

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ems.nea.exetermappingapp.settings.MainSettings

@Composable
fun DefaultSimpleButton (
    click: (() -> Unit)?,
    icon: ImageVector?,
    iconContentDescription: String,
    title: String,
    content: @Composable (() -> Unit)?,
    description: String?,
    settings: MainSettings
){
    val modifier = if (click == null) {
        Modifier
    } else {
        Modifier.clickable(
            onClick =
                click
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 8.dp),
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = iconContentDescription,
                modifier = Modifier
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title,
                fontSize = (16 * settings.textSizeMultiplier).sp
            )

            // Display the description with the text slightly transparent
            if (description != null) {
                Text(
                    text = description,
                    fontSize = (12 * settings.textSizeMultiplier).sp,
                    color = if (settings.darkTheme) {
                        Color.White.copy(alpha = 0.9f)
                    } else {
                        Color.Black.copy(alpha = 0.9f)
                    }
                )
            }
            // Display the custom content
            content?.invoke()
        }
    }
}