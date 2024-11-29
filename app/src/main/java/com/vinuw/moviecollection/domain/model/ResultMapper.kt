package com.vinuw.moviecollection.domain.model

import com.vinuw.moviecollection.data.dto.ResultDto
import com.vinuw.moviecollection.data.dto.toResultModel
import javax.inject.Inject

class ResultMapper @Inject constructor() : Function1<List<ResultDto>, List<ResultModel>> {
    override fun invoke(movieListDto: List<ResultDto>): List<ResultModel> {
        return movieListDto.map {
            it.toResultModel()
        }
    }
}