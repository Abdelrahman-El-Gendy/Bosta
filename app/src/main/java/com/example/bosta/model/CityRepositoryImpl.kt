package com.example.bosta.model


//class CityRepositoryImpl {
//
//     val apiService: CityApiService = Retrofit.Builder()
//        .baseUrl("https://stg-app.bosta.co/api/v2/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(CityApiService::class.java)
//}

import com.example.bosta.model.remote.CityApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityRepositoryImpl : CityRepository {

    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://stg-app.bosta.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApiService::class.java)
    }

    // Cache for raw API data
    private var _citiesCache = mutableListOf<Data>()
    val citiesCache: List<Data> get() = _citiesCache.toList()

    // StateFlow exposing raw API data
    private val _citiesFlow = MutableStateFlow<List<Data>>(emptyList())
    override val citiesFlow: Flow<List<Data>> = _citiesFlow

    override suspend fun getCities(): Result<List<Data>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAllCitiesWithDistricts()

            if (!response.isSuccessful) {
                return@withContext Result.failure(
                    HttpException(response)
                )
            }

            response.body()?.data?.let { apiData ->
                _citiesCache = apiData.toMutableList()
                _citiesFlow.value = _citiesCache
                Result.success(_citiesCache)
            } ?: Result.failure(
                NullPointerException("Response data was null")
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}