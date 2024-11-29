package com.vinuw.moviecollection

import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.domain.repository.MovieRepository
import com.vinuw.moviecollection.domain.usecase.GetMovieList
import com.vinuw.moviecollection.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetMovieListShould : BaseUnitTest() {

    private lateinit var getMovieList: GetMovieList
    private val repository: MovieRepository = mock()
    private val movieList: List<ResultModel> = mock()
    private val expected = Result.success(movieList)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun `getAllMovieList() should invoked at least once when use case called`() = runTest {

        mockSuccessfulCase()

        getMovieList.getAllMovieList()

        verify(repository, times(1)).getAllMovieList()
    }

    @Test
    fun `emit all the movie list received from repository`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getMovieList.getAllMovieList().first())
    }

    @Test
    fun `emit error when error received from repository`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, getMovieList.getAllMovieList().first().exceptionOrNull()!!.message)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(repository.getAllMovieList()).thenReturn(
            flow {
                emit(Result.success(movieList))
            }
        )

        getMovieList = GetMovieList(repository)
    }

    private suspend fun mockFailureCase() {
        whenever(repository.getAllMovieList()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        getMovieList = GetMovieList(repository)
    }
}