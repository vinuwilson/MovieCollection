package com.vinuw.persons.domain.usecase

import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.domain.repository.PersonsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonsList @Inject constructor(
    private val repository: PersonsRepository
) {
    suspend fun getAllPersons(): Flow<Result<List<PersonsModel>>> {
        return repository.getAllPersons()
    }
}