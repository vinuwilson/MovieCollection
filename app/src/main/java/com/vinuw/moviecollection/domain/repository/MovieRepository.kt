package com.vinuw.moviecollection.domain.repository

import com.vinuw.moviecollection.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>>
}