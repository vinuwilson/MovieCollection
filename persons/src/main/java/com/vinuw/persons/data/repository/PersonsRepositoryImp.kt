package com.vinuw.persons.data.repository

import com.vinuw.persons.domain.model.PersonsModel
import com.vinuw.persons.domain.repository.PersonsRepository
import kotlinx.coroutines.flow.Flow

class PersonsRepositoryImp(
    private val service: PersonsService
) : PersonsRepository {

    override suspend fun getAllPersons(): Flow<Result<List<PersonsModel>>> {
        return service.getAllPersons()
    }
}