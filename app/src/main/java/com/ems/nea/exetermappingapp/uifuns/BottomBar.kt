package com.ems.nea.exetermappingapp.uifuns

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.ems.nea.exetermappingapp.settings.MainSettings

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