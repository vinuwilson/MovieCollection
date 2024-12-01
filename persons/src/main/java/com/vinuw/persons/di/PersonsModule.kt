package com.vinuw.persons.di

import com.vinuw.persons.data.api.PersonsAPI
import com.vinuw.persons.data.repository.PersonsRepositoryImp
import com.vinuw.persons.data.repository.PersonsService
import com.vinuw.persons.domain.repository.PersonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersonsModule {

    @Singleton
    @Provides
    fun providePersonsRepository(service: PersonsService): PersonsRepository = PersonsRepositoryImp(service)

    @Singleton
    @Provides
    fun provideMovieAPI(retrofit: Retrofit): PersonsAPI = retrofit.create(PersonsAPI::class.java)
}