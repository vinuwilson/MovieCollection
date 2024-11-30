package com.vinuw.moviecollection.data.api

import com.vinuw.moviecollection.BuildConfig
import com.vinuw.moviecollection.data.dto.MovieDto
import retrofit2.http.GET

interface MovieAPI {

    @GET("3/movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getAllMovieList(): MovieDto
}