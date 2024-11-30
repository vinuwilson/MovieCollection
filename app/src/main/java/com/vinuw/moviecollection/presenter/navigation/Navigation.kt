package com.vinuw.moviecollection.presenter.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.vinuw.moviecollection.presenter.movie_list.MovieList
import com.vinuw.moviecollection.presenter.movie_list.MovieViewModel
import com.vinuw.moviecollection.utils.sharedViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MovieCollection,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            navigation<MovieCollection>(
                startDestination = MovieListScreen
            ) {
                composable<MovieListScreen> { entry ->
                    val viewModel =
                        entry.sharedViewModel<MovieViewModel>(navController = navController)
                    val movieState = viewModel.movieListState.collectAsStateWithLifecycle()

                    MovieList(
                        movieState = movieState.value
                    ) { movieID ->

                    }
                }
            }
        }
    }
}