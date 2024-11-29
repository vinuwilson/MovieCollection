package com.vinuw.moviecollection.presenter.movie_list

import com.vinuw.moviecollection.domain.model.ResultModel

data class MovieListState(
    val isLoading: Boolean = false,
    val movieList: List<ResultModel>? = emptyList()
)
