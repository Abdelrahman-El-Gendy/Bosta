package com.example.bosta.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bosta.presentation.citylist.CityScreen
import com.example.bosta.presentation.citylist.CityViewModel
import com.example.bosta.ui.theme.BostaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<CityViewModel>()
            val state = viewModel.state.collectAsState()

            BostaTheme {
                CityScreen(
                    state = state.value
                )
            }
        }
    }

}