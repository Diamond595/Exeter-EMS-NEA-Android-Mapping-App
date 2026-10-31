package com.ems.nea.exetermappingapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.ui.theme.ExeterMappingAppTheme
import com.ems.nea.exetermappingapp.settings.MainSettingsViewModel
import com.ems.nea.exetermappingapp.settings.MapSettings
import com.ems.nea.exetermappingapp.settings.MapSettingsViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip

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
                    mainRoadColour = Color.argb(255, 255, 244, 79),
                    motorwayRoadColour = Color.argb(255, 0, 255, 0),
                    defaultRoadColour = Color.argb(255, 255, 255, 255),
                    buildingColour = Color.argb(255, 127, 127, 127),
                    parkColour = Color.argb(255, 144, 238, 144),
                    waterColour = Color.argb(255, 118, 203, 255),
                    backgroundColour = MaterialTheme.colorScheme.background.toArgb()
                )
            )
            ExeterMappingAppTheme (
                darkTheme = mainSettings.darkTheme
            ) {
                Surface() {
                    var screen by remember { mutableStateOf("home") }

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Box(
                            modifier = Modifier
                                .weight(9f)
                        ) {
                            when (screen) {
                                "home" ->
                                    HomeScreen()

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
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            BottomBar(
                                screen = screen,
                                onScreenChange = { screen = it },
                                mainSettings = mainSettings
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(
    screen: String,
    onScreenChange: (String) -> Unit,
    mainSettings: MainSettings
){
    NavigationBar() {
        NavigationBarItem(
            selected = screen == "home",
            onClick = { onScreenChange("home") },
            icon = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                }
            },
            label = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 1) {
                    Text(
                        text = "Home",
                        fontSize = (12 * mainSettings.textSizeMultiplier).sp
                    )
                }
            }
        )
        NavigationBarItem(
            selected = screen == "test",
            onClick = { onScreenChange("test") },
            icon = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "Test"
                    )
                }
            },
            label = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 1) {
                    Text(
                        text = "Test",
                        fontSize = (12 * mainSettings.textSizeMultiplier).sp
                    )
                }
            }
        )
        NavigationBarItem(
            selected = screen == "saved",
            onClick = { onScreenChange("saved") },
            icon = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                    Icon(
                        imageVector = Icons.Filled.AddBox,
                        contentDescription = "Saved"
                    )
                }
            },
            label = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 1) {
                    Text(
                        text = "Saved",
                        fontSize = (12 * mainSettings.textSizeMultiplier).sp
                    )
                }
            }
        )
        NavigationBarItem(
            selected = screen == "settings",
            onClick = { onScreenChange("settings") },
            icon = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 2) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings"
                    )
                }
            },
            label = {
                if (mainSettings.iconStyle == 0 || mainSettings.iconStyle == 1) {
                    Text(
                        text = "Settings",
                        fontSize = (12 * mainSettings.textSizeMultiplier).sp
                    )
                }
            }
        )
    }
}