package com.example.bosta.model.di

import com.example.bosta.model.CityRepository
import com.example.bosta.model.CityRepositoryImpl
import com.example.bosta.model.remote.CityApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// di/RepositoryModule.kt
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCityRepository(apiService: CityApiService): CityRepository =
        CityRepositoryImpl(apiService)
}