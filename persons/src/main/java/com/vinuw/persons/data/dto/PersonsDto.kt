package com.vinuw.persons.data.dto

data class PersonsDto(
    val page: Int,
    val results: List<ResultDto>,
    val total_pages: Int,
    val total_results: Int
)