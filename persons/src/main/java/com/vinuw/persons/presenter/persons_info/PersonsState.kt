package com.vinuw.persons.presenter.persons_info

import com.vinuw.persons.domain.model.PersonsModel

data class PersonsState(
    val isLoading: Boolean = false,
    val personsList: List<PersonsModel>? = emptyList()
)
