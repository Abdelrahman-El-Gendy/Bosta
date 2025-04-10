package com.example.bosta

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.bosta.model.remote.CityScreenState
import com.example.bosta.presentation.citylist.CityScreen
import com.example.bosta.ui.theme.BostaTheme
import org.junit.Rule
import org.junit.Test

class CityScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_isActive() {
        testRule.setContent {
            BostaTheme {
                CityScreen(
                    state = CityScreenState(
                        cities = emptyList(),
                        isLoading = true,
                    )
                )
            }
        }
        testRule.onNodeWithContentDescription(SemanticsDescription.CITY_LIST_LOADING).assertIsDisplayed()
    }

//    @Test
//    fun errorState_isDisplayed() {
//        val errorMessage = "Test error"
//        val mockViewModel = mockk<CityViewModel>()
//        every { mockViewModel.state } returns mutableStateOf(
//            CityScreenState(
//                error = errorMessage as Throwable,
//                cities = TODO(),
//                isLoading = TODO()
//            )
//        )
//
//        testRule.setContent {
//            BostaTheme {
//                CityScreen(viewModel = mockViewModel)
//            }
//        }
//
//        testRule.onNodeWithText(errorMessage).assertIsDisplayed()
//    }
//
//    @Test
//    fun cities_areDisplayedWhenLoaded() {
//        val mockCities = listOf(
//            Data("1", "Alexandria", emptyList()),
//            Data("2", "Cairo", emptyList())
//        )
//        val mockViewModel = mockk<CityViewModel>()
//        every { mockViewModel.state } returns mutableStateOf(
//            CityScreenState(cities = mockCities)
//        )
//
//        testRule.setContent {
//            BostaTheme {
//                CityScreen(viewModel = mockViewModel)
//            }
//        }
//
//        testRule.onNodeWithText("Alexandria").assertIsDisplayed()
//        testRule.onNodeWithText("Cairo").assertIsDisplayed()
//    }
}