package com.vinuw.moviecollection.domain.usecase

import com.vinuw.moviecollection.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieList @Inject constructor(){

    suspend fun getAllMovieList(): Flow<Result<List<ResultModel>>> {
        return flow { }
    }
}