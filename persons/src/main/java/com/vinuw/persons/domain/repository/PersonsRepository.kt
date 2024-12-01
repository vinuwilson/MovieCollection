package com.vinuw.persons.domain.repository

import com.vinuw.persons.domain.model.PersonsModel
import kotlinx.coroutines.flow.Flow

interface PersonsRepository {

    suspend fun getAllPersons() : Flow<Result<List<PersonsModel>>>
}