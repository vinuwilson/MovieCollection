package com.vinuw.persons.presenter.persons_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinuw.persons.domain.usecase.PersonsList
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
class PersonsViewModel @Inject constructor(
    private val getPersonsList: PersonsList
) : ViewModel() {

    private val _personsState = MutableStateFlow(PersonsState(isLoading = true))
    val personsState = _personsState
        .onStart { getAllPersons() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PersonsState(true)
        )


    private fun getAllPersons() {
        viewModelScope.launch {
            getPersonsList.getAllPersons().collectLatest { result ->
                _personsState.update {
                    it.copy(
                        isLoading = false,
                        personsList = result.getOrDefault(emptyList())
                    )
                }
            }
        }
    }
}