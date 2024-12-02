package com.vinuw.moviecollection.presenter.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.vinuw.moviecollection.presenter.movie_details.MovieDetails
import com.vinuw.moviecollection.presenter.movie_list.MovieList
import com.vinuw.moviecollection.presenter.movie_list.MovieViewModel
import com.vinuw.moviecollection.utils.sharedViewModel
import com.vinuw.persons.presenter.persons_info.PersonInfo
import com.vinuw.persons.presenter.persons_info.PersonsViewModel

@SuppressLint("RestrictedApi")
@Composable
fun Navigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomBarItem.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.hasRoute(screen.route::class) } == true
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        icon = {
                            Icon(
                                imageVector = if (isSelected) {
                                    screen.selectedIcon
                                } else {
                                    screen.unSelectedIcon
                                },
                                contentDescription = stringResource(screen.title)
                            )
                        },
                        label = { Text(stringResource(screen.title)) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
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
                        navController.navigate(
                            MovieDetailsScreen(
                                movieID = movieID
                            )
                        )
                    }
                }

                composable<MovieDetailsScreen> { entry ->
                    val viewModel =
                        entry.sharedViewModel<MovieViewModel>(navController = navController)
                    val movieState = viewModel.movieListState.collectAsStateWithLifecycle()
                    val args = entry.toRoute<MovieDetailsScreen>()

                    MovieDetails(
                        movieState = movieState.value,
                        movieId = args.movieID
                    ) {
                        navController.navigateUp()
                    }
                }
            }

            composable<PersonsScreen> {
                val viewModel = hiltViewModel<PersonsViewModel>()
                val personsState = viewModel.personsState.collectAsStateWithLifecycle()

                PersonInfo(
                    personsState = personsState.value
                )
            }
        }
    }
}