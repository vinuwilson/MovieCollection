package com.vinuw.moviecollection.di

import com.vinuw.moviecollection.data.repository.MovieRepositoryImp
import com.vinuw.moviecollection.data.repository.MovieService
import com.vinuw.moviecollection.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(service: MovieService): MovieRepository = MovieRepositoryImp(service)

}