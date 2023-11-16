/*
package com.example.myaccounts.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myaccounts.data.Movie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn


class SearchViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")
        private set


    private val moviesFlow = flowOf(
        listOf(
            Movie(
                id = 1,
                name = "Inception",
                rating = 8.8
            ),
            Movie(
                id = 2,
                name = "The Prestige",
                rating = 8.5
            ),
            Movie(
                id = 3,
                name = "Interstellar",
                rating = 8.7
            )
        )
    )


    val searchResults: StateFlow<List<Movie>> =
        snapshotFlow { searchQuery }
            .combine(moviesFlow) { searchQuery, movies ->
                when {
                    searchQuery.isNotEmpty() -> movies.filter { movie ->
                        movie.name.contains(searchQuery, ignoreCase = true)
                    }

                    else -> movies
                }
            }.stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

}

*/
