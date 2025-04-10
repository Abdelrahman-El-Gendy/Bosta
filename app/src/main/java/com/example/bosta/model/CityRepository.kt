package com.example.bosta.model

import kotlinx.coroutines.flow.Flow

interface CityRepository {
    val citiesFlow: Flow<List<Data>>
    suspend fun getCities(): Result<List<Data>>
}