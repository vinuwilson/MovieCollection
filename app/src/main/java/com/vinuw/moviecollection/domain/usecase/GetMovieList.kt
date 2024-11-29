package com.vinuw.moviecollection.domain.usecase

import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieList @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>> {
        return repository.getAllMovieList()
    }
}