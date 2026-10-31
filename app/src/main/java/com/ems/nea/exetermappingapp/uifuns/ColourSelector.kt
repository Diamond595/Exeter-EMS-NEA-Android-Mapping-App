package com.ems.nea.exetermappingapp.uifuns

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.ems.nea.exetermappingapp.settings.MainSettings


@Composable
fun ColourSelector(
    defaultValue: Int,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    mainSettings: MainSettings
) {
    var alpha = initialValue.alpha
    var red = initialValue.red
    var green = initialValue.green
    var blue = initialValue.blue

    val rowModifier = Modifier
            .padding(8.dp)
    val textModifier = Modifier
        .padding(4.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterHorizontally,
                )
        ) {
            Text(
                text = "#${initialValue.toHexString()}",
                modifier = Modifier,
                fontSize = (24 * mainSettings.textSizeMultiplier).sp
            )
            Icon(
                imageVector = Icons.Filled.Replay,
                contentDescription = "Reset Road View Distance Size",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp)
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .clickable(
                        onClick = {
                            onValueChange(defaultValue)
                        }
                    )
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = alpha.toFloat(),
                onValueChange = {
                    alpha = it.toInt()
                    onValueChange(Color(red, green, blue, alpha).toArgb())
                },
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    activeTrackColor = Color(red, green, blue, alpha),
                )
            )
            Text(
                text = "A",
                modifier = textModifier
            )
        }
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = red.toFloat(),
                onValueChange = {
                    red = it.toInt()
                    onValueChange(Color(red, green, blue, alpha).toArgb())
                },
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    activeTrackColor = Color(red, 0, 0, 255),
                )
            )
            Text(
                text = "R",
                modifier = textModifier
            )
        }
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = green.toFloat(),
                onValueChange = {
                    green = it.toInt()
                    onValueChange(Color(red, green, blue, alpha).toArgb())
                },
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    activeTrackColor = Color(0, green, 0, 255),
                )
            )
            Text(
                text = "G",
                modifier = textModifier
            )
        }
        Row(
            modifier = rowModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Slider(
                value = blue.toFloat(),
                onValueChange = {
                    blue = it.toInt()
                    onValueChange(Color(red, green, blue, alpha).toArgb())
                },
                valueRange = 0f..255f,
                colors = SliderDefaults.colors(
                    activeTrackColor = Color(0, 0, blue, 255),
                )
            )
            Text(
                text = "B",
                modifier = textModifier
            )
        }
    }
}