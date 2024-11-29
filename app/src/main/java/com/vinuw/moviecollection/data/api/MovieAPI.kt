package com.vinuw.moviecollection.data.api

import com.vinuw.moviecollection.data.dto.MovieDto
import retrofit2.http.GET

interface MovieAPI {

    @GET("")
    suspend fun getAllMovieList(): MovieDto
}