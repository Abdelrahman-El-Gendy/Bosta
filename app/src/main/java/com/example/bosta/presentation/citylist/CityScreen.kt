package com.example.bosta.presentation.citylist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bosta.model.Data
import com.example.bosta.model.District
import com.example.bosta.ui.theme.BostaTheme

@Composable
fun CityScreen(
    viewModel: CityViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                // Handle error state
                Text("Error: ${state.error}")
            }

            else -> {
                var expandedCityId by remember { mutableStateOf<String?>(null) }

                LazyColumn {
                    items(state.cities) { city ->
                        ExpandableCityItem(
                            city = city,
                            isExpanded = city.cityId == expandedCityId,
                            onHeaderClick = {
                                expandedCityId =
                                    if (city.cityId == expandedCityId) null else city.cityId
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandableCityItem(
    city: Data,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .padding(4.dp)
            .animateContentSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // City Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onHeaderClick)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = city.cityName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand"
                )
            }

            // Districts List
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(start = 32.dp, end = 16.dp, bottom = 16.dp)) {
                    city.districts.forEach { district ->
                        DistrictItem(district = district)
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun DistrictItem(district: District) {
    Text(
        text = district.districtName,
        modifier = Modifier.padding(vertical = 8.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}


@Preview
@Composable
private fun CityScreenPreview() {
    BostaTheme {
        CityScreen()
    }
}
