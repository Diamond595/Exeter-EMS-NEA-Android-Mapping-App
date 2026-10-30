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
                text = "Map Settings Values:",
                fontSize = 30.sp
            )
            Text(
                text = "Park Colour: " + mapSettings.parkColour.toString(),
                color = Color(mapSettings.parkColour)
            )
            Log.d(TAG, mapSettings.parkColour.toString())

            Text(
                text = "Background Colour: " + mapSettings.backgroundColour.toString(),
                color = Color(mapSettings.backgroundColour)
            )
            Log.d(TAG, mapSettings.backgroundColour.toString())

            Text(
                text = "Water Colour: " + mapSettings.waterColour.toString(),
                color = Color(mapSettings.waterColour)
            )
            Log.d(TAG, mapSettings.waterColour.toString())

            Text(
                text = "Building Colour: " + mapSettings.buildingColour.toString(),
                color = Color(mapSettings.buildingColour)
            )
            Log.d(TAG, mapSettings.buildingColour.toString())

            Text(
                text = "Default Road Colour: " + mapSettings.defaultRoadColour.toString(),
                color = Color(mapSettings.defaultRoadColour)
            )
            Log.d(TAG, mapSettings.defaultRoadColour.toString())

            Text(
                text = "Main Road Colour: " + mapSettings.mainRoadColour.toString(),
                color = Color(mapSettings.mainRoadColour)
            )
            Log.d(TAG, mapSettings.mainRoadColour.toString())

            Text(
                text = "Motorway Road Colour: " + mapSettings.motorwayRoadColour.toString(),
                color = Color(mapSettings.motorwayRoadColour)
            )
            Log.d(TAG, mapSettings.motorwayRoadColour.toString())

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