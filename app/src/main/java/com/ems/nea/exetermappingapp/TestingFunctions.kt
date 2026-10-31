package com.ems.nea.exetermappingapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ems.nea.exetermappingapp.settings.MainSettings
import com.ems.nea.exetermappingapp.settings.MapSettings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red


@Composable
fun SettingsTest(
    mainSettings: MainSettings,
    mapSettings: MapSettings
){
    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
    ) {
        item {
            Text(
                text = "Main Settings Values",
                fontSize = 30.sp
            )
            Text(
                text = "Text Size: " + mainSettings.textSizeMultiplier.toString()
            )
            Log.d(TAG, mainSettings.textSizeMultiplier.toString())
            Text(
                text = "Dark Theme: " + mainSettings.darkTheme.toString()
            )
            Log.d(TAG, mainSettings.darkTheme.toString())
            Text(
                text = "Icon Style: " + mainSettings.iconStyle.toString()
            )
            Log.d(TAG, mainSettings.iconStyle.toString())
            Text(
                text = "Background Colour: " + mainSettings.backgroundColour.toHexString(),
                color = Color(mainSettings.backgroundColour.red, mainSettings.backgroundColour.green, mainSettings.backgroundColour.blue),
                modifier = Modifier
                    .background(
                        color = Color(255 - mainSettings.backgroundColour.red, 255 - mainSettings.backgroundColour.green, 255 - mainSettings.backgroundColour.blue)
                    )
            )
            Log.d(TAG, mainSettings.backgroundColour.toHexString())

            Text(
                text = "Map Settings Values:",
                fontSize = 30.sp
            )
            Text(
                text = "Park Colour: " + mapSettings.parkColour.toHexString(),
                color = Color(mapSettings.parkColour)
            )
            Log.d(TAG, mapSettings.parkColour.toHexString())

            Text(
                text = "Water Colour: " + mapSettings.waterColour.toHexString(),
                color = Color(mapSettings.waterColour)
            )
            Log.d(TAG, mapSettings.waterColour.toHexString())

            Text(
                text = "Building Colour: " + mapSettings.buildingColour.toHexString(),
                color = Color(mapSettings.buildingColour)
            )
            Log.d(TAG, mapSettings.buildingColour.toHexString())

            Text(
                text = "Default Road Colour: " + mapSettings.defaultRoadColour.toHexString(),
                color = Color(mapSettings.defaultRoadColour)
            )
            Log.d(TAG, mapSettings.defaultRoadColour.toHexString())

            Text(
                text = "Main Road Colour: " + mapSettings.mainRoadColour.toHexString(),
                color = Color(mapSettings.mainRoadColour)
            )
            Log.d(TAG, mapSettings.mainRoadColour.toHexString())

            Text(
                text = "Motorway Road Colour: " + mapSettings.motorwayRoadColour.toHexString(),
                color = Color(mapSettings.motorwayRoadColour)
            )
            Log.d(TAG, mapSettings.motorwayRoadColour.toHexString())

            Text(
                text = "Road Name View Distance: " + mapSettings.roadNameViewDistance.toString(),
            )
            Log.d(TAG, mapSettings.roadNameViewDistance.toString())

            Text(
                text = "Building View Distance: " + mapSettings.buildingViewDistance.toString(),
            )
            Log.d(TAG, mapSettings.buildingViewDistance.toString())

        }
    }
}