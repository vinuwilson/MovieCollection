package com.vinuw.moviecollection.data.repository

import com.vinuw.moviecollection.data.api.MovieAPI
import com.vinuw.moviecollection.domain.model.ResultMapper
import com.vinuw.moviecollection.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieAPI,
    private val mapper: ResultMapper
) {
    suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>> {
        return flow {
            emit(Result.success(mapper.invoke(api.getAllMovieList().results)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}