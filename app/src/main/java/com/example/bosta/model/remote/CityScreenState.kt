package com.example.bosta.model.remote

import com.example.bosta.model.Data

data class CityScreenState(
    val cities: List<Data>,
    val isLoading: Boolean,
    val error: String? = null

)