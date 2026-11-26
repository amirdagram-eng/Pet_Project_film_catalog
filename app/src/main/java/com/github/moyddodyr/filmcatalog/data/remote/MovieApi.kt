package com.github.moyddodyr.filmcatalog.data.remote

import com.github.moyddodyr.filmcatalog.data.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Path

interface MovieApi {
    @GET("movie")
    suspend fun getFilmsPage(
        @Query("token") token: String,
        @Query("page") page: Int
    ): MovieResponseDto


    @GET("/v1.5/movie/search")
    suspend fun searchMovies(
        @Header("X-API-KEY") token: String,
        @Query("page") page: Int,
        @Query("query") movieName: String
    ): MovieResponseDto

}



