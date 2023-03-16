package com.example.nowinandroid_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.window.ExperimentalMaterialWindowApi
import androidx.compose.material.window.rememberSizeClass
import androidx.core.view.WindowCompat
import com.example.nowinandroid_clone.ui.NiaApp
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialWindowApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NiaApp(rememberSizeClass())
        }
        reportFullyDrawn()
    }
}