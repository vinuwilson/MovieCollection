package com.vinuw.moviecollection.di

import com.vinuw.moviecollection.data.api.MovieAPI
import com.vinuw.moviecollection.data.repository.MovieRepositoryImp
import com.vinuw.moviecollection.data.repository.MovieService
import com.vinuw.moviecollection.domain.repository.MovieRepository
import com.vinuw.moviecollection.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMovieRepository(service: MovieService): MovieRepository = MovieRepositoryImp(service)

    @Singleton
    @Provides
    fun provideMovieAPI(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}