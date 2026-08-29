package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class RatingDTO(
    val imdb: Double? = null,
    val kp: Double? = null
)
