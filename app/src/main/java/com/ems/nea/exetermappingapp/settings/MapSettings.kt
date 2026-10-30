package com.ems.nea.exetermappingapp.settings
import android.content.Context
import android.graphics.Color
import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val Context.datastoreMap by preferencesDataStore(name = "mapSettings")

data class MapSettings (
    val buildingViewDistance: Float,
    val roadNameViewDistance: Float,
    val mainRoadColour: Int,
    val motorwayRoadColour: Int,
    val defaultRoadColour: Int,
    val buildingColour: Int,
    val parkColour: Int,
    val waterColour: Int,
    val backgroundColour: Int
) {
    companion object {
        fun default(context: Context): MapSettings {
            return MapSettings(
                buildingViewDistance = 1f,
                roadNameViewDistance = 1f,
                mainRoadColour = Color.argb(255f, 255f, 244f, 79f),
                motorwayRoadColour = Color.argb(255f, 0f, 255f, 0f),
                defaultRoadColour = Color.argb(255f, 255f, 255f, 255f),
                buildingColour = Color.argb(255f, 127f, 127f, 127f),
                parkColour = Color.argb(255f, 144f, 238f, 144f),
                waterColour = Color.argb(255f, 118f, 203f, 255f),
                backgroundColour = Color.argb(255f, 0f, 0f, 0f)
            )
        }
    }
}


class MapSettingsManager(
    private val context: Context
) {
    private object Keys {
        val BUILDING_VIEW_DISTANCE = floatPreferencesKey("building_view_distance")
        val ROAD_NAME_VIEW_DISTANCE = floatPreferencesKey("road_name_view_distance")
        val MAIN_ROAD_COLOUR = intPreferencesKey("main_road_colour")
        val MOTORWAY_ROAD_COLOUR = intPreferencesKey("motorway_road_colour")
        val DEFAULT_ROAD_COLOUR = intPreferencesKey("default_road_colour")
        val BUILDING_COLOUR = intPreferencesKey("building_colour")
        val PARK_COLOUR = intPreferencesKey("park_colour")
        val WATER_COLOUR = intPreferencesKey("water_colour")
        val BACKGROUND_COLOUR = intPreferencesKey("background_colour")

    }

    val settingsFlow: Flow<MapSettings> = context.datastoreMap.data.map { prefs ->
        MapSettings(
            buildingViewDistance = prefs[Keys.BUILDING_VIEW_DISTANCE] ?: 1f,
            roadNameViewDistance = prefs[Keys.ROAD_NAME_VIEW_DISTANCE] ?: 1f,
            mainRoadColour = prefs[Keys.MAIN_ROAD_COLOUR] ?: Color.argb(255f, 255f, 244f, 79f),
            motorwayRoadColour = prefs[Keys.MOTORWAY_ROAD_COLOUR] ?: Color.argb(255f, 0f, 255f, 0f),
            defaultRoadColour = prefs[Keys.DEFAULT_ROAD_COLOUR] ?: Color.argb(255f, 255f, 255f, 255f),
            buildingColour = prefs[Keys.BUILDING_COLOUR] ?: Color.argb(255f, 127f, 127f, 127f),
            parkColour = prefs[Keys.PARK_COLOUR] ?: Color.argb(255f, 144f, 238f, 144f),
            waterColour = prefs[Keys.WATER_COLOUR] ?: Color.argb(255f, 118f, 203f, 255f),
            backgroundColour = prefs[Keys.BACKGROUND_COLOUR] ?: Color.argb(255f, 0f, 0f, 0f)
        )
    }

    suspend fun setBuildingViewDistance(value: Float){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.BUILDING_VIEW_DISTANCE] = value
        }
    }
    suspend fun setRoadNameViewDistance(value: Float){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.ROAD_NAME_VIEW_DISTANCE] = value
        }
    }
    suspend fun setMainRoadColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.MAIN_ROAD_COLOUR] = value
        }
    }
    suspend fun setMotorwayRoadColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.MOTORWAY_ROAD_COLOUR] = value
        }
    }
    suspend fun setDefaultRoadColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.DEFAULT_ROAD_COLOUR] = value
        }
    }
    suspend fun setBuildingColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.BUILDING_COLOUR] = value
        }
    }
    suspend fun setParkColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.PARK_COLOUR] = value
        }
    }
    suspend fun setWaterColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.WATER_COLOUR] = value
        }
    }
    suspend fun setBackgroundColour(value: Int){
        context.datastoreMap.edit { prefs ->
            prefs[Keys.BACKGROUND_COLOUR] = value
        }
    }
}

class MapSettingsViewModel(
    private val settingsManager: MapSettingsManager
) : ViewModel(){
    val settingsFlow = settingsManager.settingsFlow

    fun setBuildingViewDistance(value: Float) {
        viewModelScope.launch {
            settingsManager.setBuildingViewDistance(value)
        }
    }

    fun setRoadNameViewDisatnce(value: Float){
        viewModelScope.launch {
            settingsManager.setRoadNameViewDistance(value)
        }
    }

    fun setMainRoadColour(value: Int){
        viewModelScope.launch {
            settingsManager.setMainRoadColour(value)
        }
    }

    fun setMotorwayColour(value: Int){
        viewModelScope.launch {
            settingsManager.setMotorwayRoadColour(value)
        }
    }

    fun setDefaultRoadColour(value: Int){
        viewModelScope.launch {
            settingsManager.setDefaultRoadColour(value)
        }
    }

    fun setBuildingColour(value: Int){
        viewModelScope.launch {
            settingsManager.setBuildingColour(value)
        }
    }

    fun setParkColour(value: Int){
        viewModelScope.launch {
            settingsManager.setParkColour(value)
        }
    }

    fun setWaterColour(value: Int){
        viewModelScope.launch {
            settingsManager.setWaterColour(value)
        }
    }

    fun setBackgroundColour(value: Int){
        viewModelScope.launch {
            settingsManager.setBackgroundColour(value)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                MapSettingsViewModel(MapSettingsManager(application))
            }
        }
    }
}

