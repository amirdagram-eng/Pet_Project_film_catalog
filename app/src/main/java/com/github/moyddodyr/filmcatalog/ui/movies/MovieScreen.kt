package com.github.moyddodyr.filmcatalog.ui.movies

import android.os.Message
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.moyddodyr.filmcatalog.domain.model.Movie
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun MovieScreen(
    movies: List<Movie>,
    isLoading: Boolean,
    errorMessage: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        CircularProgressIndicator(modifier = modifier)
    } else if (errorMessage != null) {
        Column(modifier = modifier) {
            Text(text = errorMessage)
            Button(onClick = onRetry) {
                Text("Повторить")
            }
        }
    } else if (movies.isEmpty()) {
        Text(text = "Фильмы не найдены", modifier = modifier)
    } else {
        LazyColumn(modifier = modifier) {
            items(movies) { currentMovie ->
                MovieItem(movie = currentMovie)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie){
    Row (
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (!movie.posterUrl.isNullOrBlank()) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 80.dp, height = 120.dp)
            )
        } else {
            Text("Нет постера")
        }

        Column(modifier = Modifier)
        {
            Text(text = movie.title ?: "Название отсутствует")
            Text(text = if (movie.ratingKp != null) "Рейтинг: ${movie.ratingKp}" else "Рейтинг отсутствует")
            Text(text = if (movie.year != null) "Год: ${movie.year}" else "Год отсутствует")
        }
    }
}

