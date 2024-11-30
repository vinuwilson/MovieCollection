package com.vinuw.moviecollection.presenter.navigation

import kotlinx.serialization.Serializable

@Serializable
object MovieCollection

@Serializable
object MovieListScreen

@Serializable
data class MovieDetailsScreen(
    val movieID: Int
)