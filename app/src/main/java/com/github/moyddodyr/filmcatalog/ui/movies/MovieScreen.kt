package com.github.moyddodyr.filmcatalog.ui.movies

import android.os.Message
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.moyddodyr.filmcatalog.domain.model.Movie
import androidx.compose.ui.Modifier

@Composable
fun MovieScreen(
    movies: List<Movie>,
    isLoading: Boolean,
    errorMessage: String?,
    modifier: Modifier = Modifier
) {
    if(isLoading){
        CircularProgressIndicator(modifier = modifier)
    } else if (errorMessage != null) {
        Text(text = errorMessage, modifier = modifier)
    } else if (movies.isEmpty()) {
        Text(text = "Фильмы не найдены", modifier = modifier)
    } else{
        LazyColumn (modifier = modifier) {
            items(movies) { movie ->
                Text(text = movie.title ?: "Название отсутствует") }
            }
        }
}

