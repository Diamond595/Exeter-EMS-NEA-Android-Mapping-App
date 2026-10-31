package com.ems.nea.exetermappingapp.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(
    screenName: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.error
            )
    ) {
        Log.e(TAG, "ERROR: $screenName is not a screen.")
        Text(
            text = "ERROR: $screenName is not a screen.",
            color = MaterialTheme.colorScheme.onError,
            fontSize = 48.sp,
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(8.dp)
        )
    }
}