package com.vinuw.persons

import com.vinuw.persons.data.api.PersonsAPI
import com.vinuw.persons.data.dto.PersonsDto
import com.vinuw.persons.data.repository.PersonsService
import com.vinuw.persons.domain.model.PersonsMapper
import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PersonsServiceShould : BaseUnitTest() {

    private lateinit var service: PersonsService
    private val mapper: PersonsMapper = mock()
    private val api: PersonsAPI = mock()
    private val personsDto: PersonsDto = mock()
    private val personsList: List<PersonsModel> = mock()
    private val expected = Result.success(personsList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun `getAllPersons() should invoked at least once when service called`() = runTest {

        mockSuccessfulCase()

        api.getAllPersons()

        verify(api, times(1)).getAllPersons()
    }

    @Test
    fun `convert persons to flow and emit`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, service.getAllPersons().first())
    }

    @Test
    fun `emit error when network fails`() = runTest {

        mockFailureCase()

        assertEquals(
            exception.message,
            service.getAllPersons().first().exceptionOrNull()!!.message
        )
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.getAllPersons()).thenReturn(personsDto)

        whenever(mapper.invoke(personsDto.results)).thenReturn(personsList)

        service = PersonsService(api, mapper)
    }

    private suspend fun mockFailureCase() {
        whenever(api.getAllPersons()).thenThrow(RuntimeException("Damn backend exception"))

        service = PersonsService(api, mapper)
    }
}