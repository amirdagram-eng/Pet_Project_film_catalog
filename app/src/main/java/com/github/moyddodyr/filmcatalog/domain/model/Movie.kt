package com.github.moyddodyr.filmcatalog.domain.model

import com.github.moyddodyr.filmcatalog.data.dto.GenreDTO

data class Movie(
    val id: Int,
    val title: String?,
    val posterUrl: String?,
    val ratingKp: Double?,
    val genres: List<GenreDTO>?,
    val year: Int? = null,
    val description: String? = null,
)