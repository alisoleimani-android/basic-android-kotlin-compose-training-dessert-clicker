package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(DessertUiState(imageResourceId = dessertList[0].imageId))
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentUiState ->
            val dessertsSold = currentUiState.totalDessertSold + 1
            val dessertIndex = determineDessertIndexToShow(dessertsSold)
            val dessert = dessertList[dessertIndex]

            currentUiState.copy(
                imageResourceId = dessert.imageId,
                totalDessertSold = dessertsSold,
                totalRevenue = currentUiState.totalRevenue + dessert.price,
                currentIndex = dessertIndex
            )
        }
    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertIndexToShow(dessertsSold: Int): Int {
        var index = 0
        for (i in dessertList.indices) {
            if (dessertsSold >= dessertList[i].startProductionAmount) {
                index = i
            } else break
        }
        return index
    }

}