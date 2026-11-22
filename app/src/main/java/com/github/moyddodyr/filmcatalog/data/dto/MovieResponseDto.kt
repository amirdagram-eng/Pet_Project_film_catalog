package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    val page: Int,
    val docs: List<MovieDto>,
    val pages: Int
)
