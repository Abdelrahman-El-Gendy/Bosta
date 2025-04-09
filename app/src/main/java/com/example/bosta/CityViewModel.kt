import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta.CityApiService
import com.example.bosta.Data
import com.example.bosta.District
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityViewModel : ViewModel() {
    private val _cities = mutableStateOf<List<Data>>(emptyList())
    val cities: List<Data> get() = _cities.value

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://stg-app.bosta.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(CityApiService::class.java)

    init {
        loadCities()
    }

    fun loadCities() {
        viewModelScope.launch {
            try {
                val response = apiService.getAllCitiesWithDistricts()
                if (response.isSuccessful) {
                    _cities.value = response.body()?.data?.map { cityData ->
                        Data(
                            cityId = cityData.cityId,
                            cityName = cityData.cityName,
                            districts = cityData.districts.map { districtData ->
                                District(
                                    districtId = districtData.districtId,
                                    districtName = districtData.districtName
                                )
                            }
                        )
                    } ?: emptyList()
                }
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }
}