package com.ems.nea.exetermappingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
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
                color = MaterialTheme.colorScheme.secondary,
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
            title = "Change Theme",
            content = null,
            description = "Change the theme of the app between light and dark mode.",
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