package com.ems.nea.exetermappingapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ems.nea.exetermappingapp.screens.ErrorScreen
import com.ems.nea.exetermappingapp.screens.HomeScreen
import com.ems.nea.exetermappingapp.screens.SavedScreen
import com.ems.nea.exetermappingapp.screens.SettingsScreen
import com.ems.nea.exetermappingapp.screens.SettingsTest
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.settings.MainSettingsViewModel
import com.ems.nea.exetermappingapp.settings.MapSettings
import com.ems.nea.exetermappingapp.settings.MapSettingsViewModel
import com.ems.nea.exetermappingapp.ui.theme.ExeterMappingAppTheme
import com.ems.nea.exetermappingapp.uifuns.BottomBar

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
                    iconStyle = 0,
                    backgroundColour = MaterialTheme.colorScheme.background.toArgb()
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
                            .background(
                                color = androidx.compose.ui.graphics.Color(mainSettings.backgroundColour),
                            )
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

                                "saved" ->
                                    SavedScreen()

                                "settings" ->
                                    SettingsScreen(
                                        mainSettings = mainSettings,
                                        mapSettings = mapSettings,
                                    )
                                else ->
                                    ErrorScreen(
                                        screenName = screen
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