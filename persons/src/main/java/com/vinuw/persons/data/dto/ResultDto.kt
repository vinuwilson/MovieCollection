package com.vinuw.persons.data.dto

import com.vinuw.persons.domain.model.PersonsModel

data class ResultDto(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)

fun ResultDto.toPersonsModel() = PersonsModel(
    adult = adult,
    gender = gender,
    id = id,
    knownForDepartment = known_for_department,
    name = name,
    originalName = original_name,
    popularity = popularity,
    profilePath = profile_path
)