package com.example.bosta.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {

    @GET("cities/getAllDistricts")
    suspend fun getAllCitiesWithDistricts(
        @Query("countryId") countryId: String = "60e4482c7cb7d4bc4849c4d5"
    ): Response<com.example.bosta.model.Response>
}