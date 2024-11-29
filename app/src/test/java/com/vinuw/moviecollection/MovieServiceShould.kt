package com.vinuw.moviecollection

import com.vinuw.moviecollection.data.api.MovieAPI
import com.vinuw.moviecollection.data.dto.MovieDto
import com.vinuw.moviecollection.data.repository.MovieService
import com.vinuw.moviecollection.domain.model.ResultMapper
import com.vinuw.moviecollection.domain.model.ResultModel
import com.vinuw.moviecollection.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieServiceShould : BaseUnitTest() {

    private lateinit var service: MovieService
    private val mapper: ResultMapper = mock()
    private val api: MovieAPI = mock()
    private val movieDto: MovieDto = mock()
    private val movieList: List<ResultModel> = mock()
    private val expected = Result.success(movieList)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun `getAllMovieList() should invoked at least once when service called`() = runTest {

        mockSuccessfulCase()

        api.getAllMovieList()

        verify(api, times(1)).getAllMovieList()
    }

    @Test
    fun `convert movie list to flow and emit`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, service.getAllMovieList().first())
    }

    @Test
    fun `emit error when network fails`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            service.getAllMovieList().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getAllMovieList()).thenReturn(movieDto)

        whenever(mapper.invoke(movieDto.results)).thenReturn(movieList)

        service = MovieService(api, mapper)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getAllMovieList()).thenThrow(RuntimeException("Damn backend exception"))

        service = MovieService(api, mapper)
    }
}