package com.vinuw.moviecollection

import com.vinuw.moviecollection.data.repository.MovieRepositoryImp
import com.vinuw.moviecollection.data.repository.MovieService
import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.domain.repository.MovieRepository
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

class MovieRepositoryShould : BaseUnitTest() {

    private lateinit var repository: MovieRepository
    private val service: MovieService = mock()
    private val movieList: List<ResultModel> = mock()
    private val expected = Result.success(movieList)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun `getAllMovieList() should invoked at least once when repository called`() = runTest {

        mockSuccessfulCase()

        repository.getAllMovieList()

        verify(service, times(1)).getAllMovieList()
    }

    @Test
    fun `emit all the movie list received from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, repository.getAllMovieList().first())
    }

    @Test
    fun `emit error when error received from service`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            repository.getAllMovieList().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getAllMovieList()).thenReturn(
            flow {
                emit(Result.success(movieList))
            }
        )

        repository = MovieRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getAllMovieList()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        repository = MovieRepositoryImp(service)
    }
}