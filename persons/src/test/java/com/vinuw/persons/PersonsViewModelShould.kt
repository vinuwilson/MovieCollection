package com.vinuw.persons

import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.domain.usecase.PersonsList
import com.vinuw.persons.presenter.persons_info.PersonsState
import com.vinuw.persons.presenter.persons_info.PersonsViewModel
import com.vinuw.persons.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PersonsViewModelShould : BaseUnitTest(){

    private lateinit var viewModel: PersonsViewModel
    private val getPersonList: PersonsList = mock()
    private val personsList: List<PersonsModel> = mock()
    private val expected = PersonsState(false, personsList)
    private val exception = PersonsState()

    @Test
    fun `getAllPerson() should invoked at least once when viewmodel called`() = runTest {

        mockSuccessfulCase()

        viewModel.personsState.first()

        verify(getPersonList, times(1)).getAllPersons()
    }

    @Test
    fun `emit state with all persons when success received from use case`() = runTest {

        mockSuccessfulCase()

        assertEquals(expected, viewModel.personsState.first())
    }

    @Test
    fun `emit state with empty list when error received from use case`() = runTest {

        mockFailureCase()

        assertEquals(exception.personsList, viewModel.personsState.value.personsList)
    }

    @Test
    fun `show loader while fetching data`() = runTest {

        mockSuccessfulCase()

        assertEquals(true, viewModel.personsState.value.isLoading)
    }

    @Test
    fun `hide loader after fetching data`() = runTest {

        mockSuccessfulCase()

        assertEquals(false, viewModel.personsState.first().isLoading)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(getPersonList.getAllPersons()).thenReturn(
            flow {
                emit(Result.success(personsList))
            }
        )

        viewModel = PersonsViewModel(getPersonList)
    }


    private suspend fun mockFailureCase() {
        whenever(getPersonList.getAllPersons()).thenReturn(
            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        )

        viewModel = PersonsViewModel(getPersonList)
    }
}