package com.ems.nea.exetermappingapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.ui.theme.ExeterMappingAppTheme
import com.ems.nea.exetermappingapp.settings.MainSettingsViewModel
import com.ems.nea.exetermappingapp.settings.MapSettings
import com.ems.nea.exetermappingapp.settings.MapSettingsViewModel

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
            ExeterMappingAppTheme {

            }
        }

    }
}
