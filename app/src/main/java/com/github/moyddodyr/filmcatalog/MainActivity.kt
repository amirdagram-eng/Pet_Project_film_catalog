package com.github.moyddodyr.filmcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.moyddodyr.filmcatalog.ui.theme.FilmCatalogTheme
import androidx.lifecycle.lifecycleScope
import com.github.moyddodyr.filmcatalog.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import android.util.Log
import com.github.moyddodyr.filmcatalog.ui.movies.MovieScreen
import kotlinx.coroutines.CancellationException
import okio.IOException
import retrofit2.HttpException
import kotlinx.serialization.SerializationException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.moyddodyr.filmcatalog.data.mapper.toDomainModel
import com.github.moyddodyr.filmcatalog.domain.model.Movie

class MainActivity : ComponentActivity() {
    private var movies by mutableStateOf<List<Movie>>(emptyList())
    private var isLoading by mutableStateOf(false)
    private var errorMessage by mutableStateOf<String?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmCatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MovieScreen(
                        movies = movies,
                        isLoading= isLoading,
                        errorMessage = errorMessage,
                        modifier = Modifier.padding(innerPadding)
                        )

                }
            }
        }
        lifecycleScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = RetrofitInstance.api.getFilmsPage(
                    token = BuildConfig.TMDB_API_KEY,
                    page = 1
                )
                movies = result.docs.map { movieDto ->
                    movieDto.toDomainModel()
                }

            } catch (e: CancellationException){
                throw e
            } catch (e: IOException){
                Log.e("Movies", "Не удалось получить данные: ошибка соединения", e)
                errorMessage = "Не удалось загрузить фильмы. Проверьте подключение к интернету."
            } catch (e: HttpException) {
                Log.e("Movies", "Ошибка сервера: HTTP ${e.code()}", e)
                errorMessage = "Не удалось загрузить фильмы. HTTP ${e.code()}."
            } catch (e: SerializationException) {
                Log.e("Movies", "Не удалось разобрать ответ сервера", e)
                errorMessage = "Не удалось обработать ответ сервера."
            } finally {
                isLoading = false
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilmCatalogTheme {
        Greeting("Android")
    }
}