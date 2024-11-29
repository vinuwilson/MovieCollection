package com.vinuw.moviecollection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.vinuw.moviecollection.presenter.navigation.Navigation
import com.vinuw.moviecollection.ui.theme.MovieCollectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApp()
        }
    }
}

@Composable
fun MovieApp() {
    MovieCollectionTheme {
        Navigation()
    }
}