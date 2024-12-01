package com.vinuw.persons

import com.vinuw.persons.data.repository.PersonsRepositoryImp
import com.vinuw.persons.data.repository.PersonsService
import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.domain.repository.PersonsRepository
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

class PersonsRepositoryShould : BaseUnitTest() {

    private lateinit var repository: PersonsRepository
    private val service: PersonsService = mock()
    private val personsList: List<PersonsModel> = mock()
    private val expected = Result.success(personsList)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun `getAllPersons() should invoked at least once when repository called`() = runTest {

        mockSuccessfulCase()

        repository.getAllPersons()

        verify(service, times(1)).getAllPersons()
    }

    @Test
    fun `emit all the persons received from service`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, repository.getAllPersons().first())
    }

    @Test
    fun `emit error when error received from service`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            repository.getAllPersons().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.getAllPersons()).thenReturn(
            flow {
                emit(Result.success(personsList))
            }
        )

        repository = PersonsRepositoryImp(service)
    }

    private suspend fun mockFailureCase() {
        whenever(service.getAllPersons()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        repository = PersonsRepositoryImp(service)
    }
}