package com.vinuw.persons

import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.domain.repository.PersonsRepository
import com.vinuw.persons.domain.usecase.PersonsList
import com.vinuw.persons.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PersonsListShould : BaseUnitTest() {

    private lateinit var getPersonsList: PersonsList
    private val repository: PersonsRepository = mock()
    private val personsList: List<PersonsModel> = mock()
    private val expected = Result.success(personsList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `getAllPersons() should invoked at least once when use case called`() = runTest {

        mockSuccessfulCase()

        getPersonsList.getAllPersons()

        verify(repository, times(1)).getAllPersons()
    }

    @Test
    fun `emit all the persons received from repository`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, getPersonsList.getAllPersons().first())
    }

    @Test
    fun `emit error when error received from repository`() = runTest {

        mockFailureCase()

        assertEquals(exception.message, getPersonsList.getAllPersons().first().exceptionOrNull()!!.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(repository.getAllPersons()).thenReturn(
            flow {
                emit(Result.success(personsList))
            }
        )

        getPersonsList = PersonsList(repository)
    }

    private suspend fun mockFailureCase() {
        whenever(repository.getAllPersons()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        getPersonsList = PersonsList(repository)
    }
}