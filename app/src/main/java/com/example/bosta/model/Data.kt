package com.example.bosta.model

//data class City(
//    val id: String,
//    val cityName: String
//)
//
//val listCity = listOf<City>(
//    City("1", "Cairo"),
//    City("2", "Gharbia"),
//    City("3", "Alex"),
//    City("4", "Banha"),
//    City("5", "Mansoura"),
//    City("6", "Giza"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//    City("7", "London"),
//    City("8", "Suez"),
//    City("9", "6 October"),
//
//    )

data class Response(
    val `data`: List<Data>,
)

data class Data(
    val cityId: String,
    val cityName: String,
    val districts: List<District>,
)

data class District(
    val districtId: String,
    val districtName: String,

)