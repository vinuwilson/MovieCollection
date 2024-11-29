package com.vinuw.moviecollection.presenter.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinuw.moviecollection.domain.usecase.GetMovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieList: GetMovieList
) : ViewModel() {

    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState
    .onStart { getAllMovieList() }
    .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MovieListState(true)
    )

    private fun getAllMovieList() {
        viewModelScope.launch {
            getMovieList.getAllMovieList().collectLatest { result ->
                _movieListState.update {
                    it.copy(
                        isLoading = false,
                        movieList = result.getOrDefault(emptyList())
                    )
                }
            }
        }
    }

}