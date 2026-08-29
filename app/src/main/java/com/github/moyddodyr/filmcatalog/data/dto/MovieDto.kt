package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val genres: List<GenreDTO>? = null,
    val countries: List<CountryDTO>? = null,
    val rating: RatingDTO? = null,
    val poster: PosterDTO? = null,
    val ratingMpaa: String? = null
)