package com.vinuw.moviecollection.data.dto

import com.vinuw.moviecollection.domain.model.ResultModel

data class ResultDto(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun ResultDto.toResultModel() = ResultModel(
    backdropPath = backdrop_path,
    id = id,
    originalLanguage = original_language,
    originalTitle = original_title,
    overview = overview,
    posterPath = poster_path,
    releaseDate = release_date,
    title = title
)