package com.vinuw.moviecollection

import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.domain.usecase.GetMovieList
import com.vinuw.moviecollection.presenter.movie_list.MovieListState
import com.vinuw.moviecollection.presenter.movie_list.MovieViewModel
import com.vinuw.moviecollection.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: MovieViewModel
    private val getMovieList: GetMovieList = mock()
    private val movieList: List<ResultModel> = mock()
    private val expected = MovieListState( false, movieList)
    private val exception = MovieListState()

    @Test
    fun `getAllMovieList() should invoked at least once when viewmodel called`() = runTest {

        mockSuccessfulCase()

        viewModel.movieListState.first()

        verify(getMovieList, times(1)).getAllMovieList()
    }

    @Test
    fun `emit state with movie list when success received from use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.movieListState.first())
    }

    @Test
    fun `emit state with empty list when error received from use case`() = runTest {

        mockFailureCase()

        assertEquals(exception.movieList, viewModel.movieListState.value.movieList)
    }

    @Test
    fun `show loader while fetching data`() = runTest {

        mockSuccessfulCase()

        assertEquals(true, viewModel.movieListState.value.isLoading)
    }

    @Test
    fun `hide loader after fetching data`() = runTest {

        mockSuccessfulCase()

        assertEquals(false, viewModel.movieListState.first().isLoading)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(getMovieList.getAllMovieList()).thenReturn(
            flow {
                emit(Result.success(movieList))
            }
        )

        viewModel = MovieViewModel(getMovieList)
    }


    private suspend fun mockFailureCase() {
        whenever(getMovieList.getAllMovieList()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        viewModel = MovieViewModel(getMovieList)
    }
}