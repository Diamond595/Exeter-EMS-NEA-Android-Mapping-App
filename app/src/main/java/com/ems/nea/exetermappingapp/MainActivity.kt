package com.ems.nea.exetermappingapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.ui.theme.ExeterMappingAppTheme
import com.ems.nea.exetermappingapp.settings.MainSettingsViewModel
import com.ems.nea.exetermappingapp.settings.MapSettings
import com.ems.nea.exetermappingapp.settings.MapSettingsViewModel
import com.ems.nea.exetermappingapp.ui.theme.SettingsScreen

class MainActivity : ComponentActivity() {
    val mainSettingsViewModel: MainSettingsViewModel by viewModels { MainSettingsViewModel.Factory }
    val mapSettingsViewModel: MapSettingsViewModel by viewModels { MapSettingsViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainSettings by mainSettingsViewModel.settingsFlow.collectAsStateWithLifecycle(
                initialValue = MainSettings(
                    darkTheme = isSystemInDarkTheme(),
                    textSizeMultiplier = 1f,
                    iconStyle = 0
                )
            )
            val mapSettings by mapSettingsViewModel.settingsFlow.collectAsStateWithLifecycle(
                initialValue = MapSettings(
                    buildingViewDistance = 1f,
                    roadNameViewDistance = 1f,
                    mainRoadColour = Color.argb(255f, 255f, 244f, 79f),
                    motorwayRoadColour = Color.argb(255f, 0f, 255f, 0f),
                    defaultRoadColour = Color.argb(255f, 255f, 255f, 255f),
                    buildingColour = Color.argb(255f, 127f, 127f, 127f),
                    parkColour = Color.argb(255f, 144f, 238f, 144f),
                    waterColour = Color.argb(255f, 118f, 203f, 255f),
                    backgroundColour = MaterialTheme.colorScheme.background.toArgb()
                )
            )
            ExeterMappingAppTheme (
                darkTheme = mainSettings.darkTheme
            ) {
                var screen = remember { "home" }
                Surface() {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        when (screen) {
                            "home" ->
                                TODO()

                            "test" ->
                                SettingsTest(
                                    mainSettings,
                                    mapSettings
                                )

                            "settings" ->
                                SettingsScreen(
                                    mainSettings = mainSettings,
                                    mapSettings = mapSettings,
                                )
                        }
                        NavigationBar() {
                            NavigationBarItem(
                                selected = screen == "home",
                                onClick = { screen = "home" },
                                icon = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 3) {
                                        Icon(
                                            imageVector = Icons.Filled.Home,
                                            contentDescription = "Home"
                                        )
                                    }
                                },
                                label = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                                        Text(
                                            text = "Home",
                                            fontSize = (12 * mainSettings.textSizeMultiplier).sp
                                        )
                                    }
                                }
                            )
                            NavigationBarItem(
                                selected = screen == "test",
                                onClick = { screen = "test" },
                                icon = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 3) {
                                        Icon(
                                            imageVector = Icons.Filled.Build,
                                            contentDescription = "Test"
                                        )
                                    }
                                },
                                label = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                                        Text(
                                            text = "Test",
                                            fontSize = (12 * mainSettings.textSizeMultiplier).sp
                                        )
                                    }
                                }
                            )
                            NavigationBarItem(
                                selected = screen == "saved",
                                onClick = { screen = "saved" },
                                icon = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 3) {
                                        Icon(
                                            imageVector = Icons.Filled.AddBox,
                                            contentDescription = "Saved"
                                        )
                                    }
                                },
                                label = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                                        Text(
                                            text = "Saved",
                                            fontSize = (12 * mainSettings.textSizeMultiplier).sp
                                        )
                                    }
                                }
                            )
                            NavigationBarItem(
                                selected = screen == "settings",
                                onClick = { screen = "settings" },
                                icon = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 3) {
                                        Icon(
                                            imageVector = Icons.Filled.Settings,
                                            contentDescription = "Settings"
                                        )
                                    }
                                },
                                label = {
                                    if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                                        Text(
                                            text = "Settings",
                                            fontSize = (12 * mainSettings.textSizeMultiplier).sp
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
