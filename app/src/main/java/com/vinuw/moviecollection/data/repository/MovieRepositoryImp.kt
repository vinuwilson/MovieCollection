package com.vinuw.moviecollection.data.repository

import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val service: MovieService
) : MovieRepository {

    override suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>> {
        return service.getAllMovieList()
    }
}