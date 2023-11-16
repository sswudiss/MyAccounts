package com.example.myaccounts.data

import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

data class Movie(
    val id: Long,
    val name: String,
    val rating: Double
)
