/*
package com.example.myaccounts.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.MyAccountsTheme
import com.example.myaccounts.data.Movie


@Composable
fun SearchScreenBar(viewModel: SearchViewModel) {
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    SearchScreen(
        searchQuery = viewModel.searchQuery,
        searchResults = searchResults,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<Movie>,
    onSearchQueryChange: (String) -> Unit
) {
    androidx.compose.material3.SearchBar(
        query = searchQuery,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = "Search movies")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    count = searchResults.size,
                    key = { index -> searchResults[index].id },
                    itemContent = { index ->
                        val movie = searchResults[index]
                        MovieListItem(movie = movie)
                    }
                )
            }

        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp


    )
}


@Composable
fun MovieListItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = movie.name)
        Text(text = movie.rating.toString())
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    MyAccountsTheme {
        val searchQuery = "In"

        val searchResults = listOf(
            Movie(
                id = 1,
                name = "Interstellar",
                rating = 8.7
            ),
            Movie(
                id = 2,
                name = "Inception",
                rating = 8.8
            )
        )

        SearchScreen(
            searchQuery = searchQuery,
            searchResults = searchResults,
            onSearchQueryChange = {}
        )
    }
}*/
