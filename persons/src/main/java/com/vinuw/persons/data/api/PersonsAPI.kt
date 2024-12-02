package com.vinuw.persons.data.api

import com.vinuw.persons.BuildConfig
import com.vinuw.persons.data.dto.PersonsDto
import retrofit2.http.GET

interface PersonsAPI {

    @GET("3/person/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getAllPersons() : PersonsDto
}