package com.vinuw.persons.data.repository

import com.vinuw.persons.data.api.PersonsAPI
import com.vinuw.persons.domain.model.PersonsMapper
import com.vinuw.persons.domain.model.PersonsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonsService @Inject constructor(
    private val api: PersonsAPI,
    private val mapper: PersonsMapper
) {
    suspend fun getAllPersons(): Flow<Result<List<PersonsModel>>> {
        return flow {
            emit(Result.success(mapper.invoke(api.getAllPersons().results)))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}