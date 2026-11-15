package com.github.moyddodyr.filmcatalog.ui.movies

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

@Composable
fun MovieScreen(
    movieNames: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier){
        items(movieNames){movieName ->
            Text(text = movieName) }
        }
    }

