package com.vinuw.moviecollection.data.repository

import com.vinuw.moviecollection.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieService @Inject constructor(

) {
    suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>> {
        return flow { }
    }
}