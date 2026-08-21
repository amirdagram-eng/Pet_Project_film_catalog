package com.github.moyddodyr.filmcatalog.data.remote

import okhttp3.MediaType.Companion.toMediaType    // Преобразует строку в MediaType объект, который необходим для Retrofit.
import kotlinx.serialization.json.Json            // Парсинг текста в Kotlin-объекты и обратно
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory // Связывает ретрофит и сериализацию, заставляя работать вместе

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val json = Json{        // Внутри скобок будут правила, по которым библиотека будет читать JSON от сервера
    ignoreUnknownKeys = true        // Игнор неизвестных полей в JSON
    isLenient = true                // Позволяет парсеру прощать мелкие несоответствия JSON (строки без кавычек, лишние запятые)
    }
    val retrofit: Retrofit by lazy {  // Блок сработает только в момент вызова синглтона.
        val contentType = "application/json".toMediaType()   // Указываем сети, что будем обменивать данными только в JSON-формате
        Retrofit.Builder()     // Паттерн, задача которого пошагово собрать и настроить Retrofit
            .baseUrl(BASE_URL)   // Retrofit запоминает главный адрес сервера, к которому позже сам будет прибавлять пути
            .addConverterFactory(json.asConverterFactory(contentType)) // Отдаем Ретру объекту JSON и contentType
            .build()
    }
    val api: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}