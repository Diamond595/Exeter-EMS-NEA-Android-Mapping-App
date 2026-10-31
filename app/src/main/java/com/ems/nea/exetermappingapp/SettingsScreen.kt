package com.ems.nea.exetermappingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.contentType
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorInt
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.settings.MainSettingsViewModel
import com.ems.nea.exetermappingapp.settings.MapSettings
import com.ems.nea.exetermappingapp.settings.MapSettingsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingsScreen(
    mainSettings: MainSettings,
    mapSettings: MapSettings,
    mainSettingsViewModel: MainSettingsViewModel = viewModel(factory = MainSettingsViewModel.Factory),
    mapSettingsViewModel: MapSettingsViewModel = viewModel(factory = MapSettingsViewModel.Factory),
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .statusBarsPadding()
    ) {
        item {
            MainSettingsSection(
                mainSettings = mainSettings,
                mainSettingsViewModel = mainSettingsViewModel
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )

            MapSettingsSection(
                mapSettings = mapSettings,
                mainSettings = mainSettings,
                mapSettingsViewModel = mapSettingsViewModel
            )
        }
    }
}

@Composable
fun MainSettingsSection(
    mainSettings: MainSettings,
    mainSettingsViewModel: MainSettingsViewModel = viewModel(factory = MainSettingsViewModel.Factory)
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            text = "Main Settings:",
            fontSize = (30 * mainSettings.textSizeMultiplier).sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        )
        DefaultSimpleButton(
            click = { mainSettingsViewModel.toggleTheme(!mainSettings.darkTheme) },
            icon = if (mainSettings.darkTheme) {
                Icons.Filled.DarkMode
            } else {
                Icons.Filled.LightMode
            },
            iconContentDescription = "Toggle Theme",
            title = "Theme",
            content = null,
            description = "Change the theme of the app between light and dark mode.",
            settings = mainSettings
        )
        DefaultSimpleButton(
            click = null,
            icon = Icons.Filled.TextFields,
            iconContentDescription = "Change Text Size",
            title = "Text Size",
            content = @Composable {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row(
                        modifier = Modifier
                            .weight(8f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "-",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(4.dp),
                            fontSize = 24.sp
                        )
                        Slider(
                            value = mainSettings.textSizeMultiplier,
                            onValueChange = {
                                mainSettingsViewModel.setTextSizeMultiplier(it)
                            },
                            valueRange = 0.5f..2f,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically),
                        )
                        Text(
                            text = "+",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .padding(4.dp),
                            fontSize = 24.sp

                        )
                    }

                        Icon(
                            imageVector = Icons.Filled.Replay,
                            contentDescription = "Reset Text Size",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(
                                    shape = MaterialTheme.shapes.small,
                                )
                                .clickable(
                                    onClick = {
                                        mainSettingsViewModel.setTextSizeMultiplier(1f)
                                    }
                                )
                                .weight(2f)
                                .align(alignment = Alignment.CenterVertically)
                        )
                }
            },
            description = "Change the size of the text in the app.",
            settings = mainSettings
        )
        DefaultSimpleButton(
            click = null,
            icon = Icons.Filled.Image,
            iconContentDescription = "Change Icon Style",
            title = "Icon Style",
            content = @Composable {
                NavigationBar() {
                    NavigationBarItem(
                        selected = mainSettings.iconStyle == 0,
                        onClick = { mainSettingsViewModel.setIconStyle(0) },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Image,
                                contentDescription = "Icon Style"
                            )
                        },
                        label = {
                            Text(
                                text = "Label",
                                fontSize = (12 * mainSettings.textSizeMultiplier).sp
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = mainSettings.iconStyle == 1,
                        onClick = { mainSettingsViewModel.setIconStyle(1) },
                        icon = {

                        },
                        label = {
                            Text(
                                text = "Label",
                                fontSize = (12 * mainSettings.textSizeMultiplier).sp
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = mainSettings.iconStyle == 2,
                        onClick = { mainSettingsViewModel.setIconStyle(2) },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Image,
                                contentDescription = "Icon Style"
                            )
                        }
                    )
                }
            },
            description = "Change how the navigation bar is displayed.",
            settings = mainSettings
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
    }
}

@Composable
fun MapSettingsSection(
    mapSettings: MapSettings,
    mainSettings: MainSettings,
    mapSettingsViewModel: MapSettingsViewModel = viewModel(factory = MapSettingsViewModel.Factory)
){
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            text = "Main Settings:",
            fontSize = (30 * mainSettings.textSizeMultiplier).sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(8.dp)
        )

        DefaultSimpleButton(
            click = null,
            icon = Icons.Filled.ColorLens,
            iconContentDescription = "Change Default Road Colour",
            title = "Default Road Colour",
            content =
            @Composable {
                Slider(
                    value = mapSettings.defaultRoadColour.toFloat(),
                    onValueChange = {
                        mapSettingsViewModel.setDefaultRoadColour(it.toInt())
                    },
                    valueRange = -16777216f..-1f,
                    modifier = Modifier,
                )
            },
            description = "Change the colour of the default roads on the map.",
            settings = mainSettings
        )
    }
}

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