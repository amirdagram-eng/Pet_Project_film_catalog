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
import kotlinx.coroutines.CancellationException
import okio.IOException
import retrofit2.HttpException
import kotlinx.serialization.SerializationException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmCatalogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        lifecycleScope.launch {
            try {
                val result = RetrofitInstance.api.getFilmsPage(
                    token = BuildConfig.TMDB_API_KEY,
                    page = 1
                )
                val moviesCount = result.docs.size //получаем кол-во фильмов
                val firstMovieName = result.docs.firstOrNull()?.name
                    ?: "Название отсутствует" //вызов имени первого фильма, если нет - то, что в кавычках
                Log.d("Movies", "Количество фильмов: $moviesCount")
                Log.d("Movies", "Название первого фильма: $firstMovieName")

            } catch (e: CancellationException){
                throw e
            } catch (e: IOException){
                Log.e("Movies", "Не удалось получить данные: ошибка соединения", e)
            } catch (e: HttpException) {
                Log.e("Movies", "Ошибка сервера: HTTP ${e.code()}", e)
            } catch (e: SerializationException) {
                Log.e("Movies", "Не удалось разобрать ответ сервера", e)
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