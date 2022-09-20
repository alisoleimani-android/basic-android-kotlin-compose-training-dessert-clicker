package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class DessertUiState(
    @DrawableRes val imageResourceId: Int,
    val currentIndex: Int = 0,
    val totalDessertSold: Int = 0,
    val totalRevenue: Int = 0
)
