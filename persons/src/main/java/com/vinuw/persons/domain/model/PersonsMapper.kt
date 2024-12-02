package com.vinuw.persons.domain.model


import com.vinuw.persons.data.dto.ResultDto
import com.vinuw.persons.data.dto.toPersonsModel
import javax.inject.Inject

class PersonsMapper @Inject constructor() : Function1<List<ResultDto>, List<PersonsModel>> {
    override fun invoke(personsDto: List<ResultDto>): List<PersonsModel> {
        return personsDto.map {
            it.toPersonsModel()
        }
    }
}