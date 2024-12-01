package com.vinuw.moviecollection.presenter.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.vinuw.moviecollection.R

data class BottomNavigationItem<T : Any>(
    @StringRes val title: Int,
    val route: T,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

val bottomBarItem = listOf(
    BottomNavigationItem(
        title = R.string.movies,
        route = MovieListScreen,
        selectedIcon = Icons.Filled.Movie,
        unSelectedIcon = Icons.Outlined.Movie
    ),
    BottomNavigationItem(
        title = R.string.persons,
        route = PersonsScreen,
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
)