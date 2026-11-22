package com.github.moyddodyr.filmcatalog.ui.movies

import android.os.Message
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun MovieScreen(
    movies: List<Movie>,
    isLoading: Boolean,
    errorMessage: String?,
    onRetry: () -> Unit,
    onPageClick: (Int) -> Unit,
    //onNext: () -> Unit,
    currentPage: Int,
    totalPages: Int,
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
    }  else {
    Column (
        modifier = modifier.fillMaxWidth()
    )
    {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(movies) { currentMovie ->
                MovieItem(movie = currentMovie)
            }
        }

        if(totalPages > 0){
            var visibleCount = minOf(totalPages, 5)
            var firstPage = (currentPage - 2).coerceIn(1, totalPages - visibleCount + 1)
            var lastPage = firstPage + visibleCount - 1

            Row {
                for (page in firstPage..lastPage) {
                    Button(onClick = { onPageClick(page) }) {
                        Text(text = page.toString())
                    }
                }
            }
        }
    }
    }
}


@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val posterModifier = Modifier
            .size(width = 80.dp, height = 120.dp)
            .clip(RoundedCornerShape(8.dp))
        if (!movie.posterUrl.isNullOrBlank()) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                error = painterResource(android.R.drawable.ic_menu_report_image),
                placeholder = painterResource(android.R.drawable.ic_menu_gallery),
                modifier = posterModifier


            )
        } else {
            Box(
                modifier = posterModifier
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Нет постера",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }


        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        )
        {
            Text(
                text = if (!movie.title.isNullOrBlank()) movie.title else "Название отсутствует",
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(text = if (movie.ratingKp != null) "Рейтинг: ${movie.ratingKp}" else "Рейтинг отсутствует")
            Text(text = if (movie.year != null) "Год: ${movie.year}" else "Год отсутствует")
        }
    }
}
