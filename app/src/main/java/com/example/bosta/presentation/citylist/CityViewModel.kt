package com.example.bosta.presentation.citylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosta.model.CityRepository
import com.example.bosta.model.CityRepositoryImpl
import com.example.bosta.model.remote.CityScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        CityScreenState(
            cities = emptyList(),
            isLoading = true,
        )
    )
    val state: StateFlow<CityScreenState> = _state.asStateFlow()

    init {
        loadCities()
    }

    fun loadCities() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            repository.getCities()
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            cities = data,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            error = error.toString(),
                            isLoading = false
                        )
                    }
                }
        }
    }
}
/**
class CityViewModel : ViewModel() {
private val _state = MutableStateFlow(
CityScreenState(
cities = emptyList(),
isLoading = true,
)
)

var state: StateFlow<CityScreenState> = _state.asStateFlow()

private val cityRepository: CityRepositoryImpl = CityRepositoryImpl()

private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
_state.update { currentState ->
currentState.copy(
isLoading = false,
error = throwable
)
}
// Optional: Log the error
Log.e("CityViewModel", "Coroutine failed", throwable)
}


init {
loadCities()
}

fun loadCities() {
_state.update { it.copy(isLoading = true, error = null) }

viewModelScope.launch(exceptionHandler) {
try {
val response = cityRepository.apiService.getAllCitiesWithDistricts()
if (!response.isSuccessful) {
throw HttpException(response) // Convert to exception
}

_state.update {
it.copy(
cities = response.body()?.data ?: emptyList(),
isLoading = false
)
}
} catch (e: Exception) {
// This will be caught by our exceptionHandler
throw e // Re-throw to let the handler process it
}
}
}
}
 **/
