package com.ems.nea.exetermappingapp.settings

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
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

private val Context.datastoreMain by preferencesDataStore(name = "mainSettings")

data class MainSettings (
    val darkTheme: Boolean,
    val textSizeMultiplier: Float,
    val iconStyle: Int
) {
    companion object {
        fun default(context: Context): MainSettings {
            return MainSettings(
                darkTheme = true,
                textSizeMultiplier = 1f,
                iconStyle = 0,
            )
        }
    }
}

class MainSettingsManager(
    private val context: Context
) {
    private object Keys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val TEXT_SIZE_MULTIPLIER = floatPreferencesKey("text_size_multiplier")
        val ICON_STYLE = intPreferencesKey("icon_style")
    }

    val settingsFlow: Flow<MainSettings> = context.datastoreMain.data.map { prefs ->
        MainSettings(
            darkTheme = prefs[Keys.DARK_THEME] ?: false,
            textSizeMultiplier = prefs[Keys.TEXT_SIZE_MULTIPLIER] ?: 1f,
            iconStyle = prefs[Keys.ICON_STYLE] ?: 0
        )
    }

    suspend fun setDarkTheme(enabled: Boolean){
        context.datastoreMain.edit { prefs ->
            prefs[Keys.DARK_THEME] = enabled
        }
    }

    suspend fun setTextSizeMultiplier(textSizeMultiplier: Float){
        context.datastoreMain.edit { prefs ->
            prefs[Keys.TEXT_SIZE_MULTIPLIER] = textSizeMultiplier
        }
    }

    suspend fun setIconStyle(iconStyle: Int){
        context.datastoreMain.edit { prefs ->
            prefs[Keys.ICON_STYLE] = iconStyle
        }
    }
}

class MainSettingsViewModel(
    private val settingsManager: MainSettingsManager
) : ViewModel(){
    val settingsFlow = settingsManager.settingsFlow

    fun toggleTheme(isDark: Boolean){
        viewModelScope.launch {
            settingsManager.setDarkTheme(isDark)
        }
    }

    fun setTextSizeMultiplier(textSizeMultiplier: Float){
        viewModelScope.launch {
            settingsManager.setTextSizeMultiplier(textSizeMultiplier)
        }
    }

    fun setIconStyle(iconStyle: Int){
        viewModelScope.launch {
            settingsManager.setIconStyle(iconStyle)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                MainSettingsViewModel(MainSettingsManager(application))
            }
        }
    }
}
