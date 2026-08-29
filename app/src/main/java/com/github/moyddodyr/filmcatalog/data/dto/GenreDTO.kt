package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    val id: Int,
    val name: String,
    val slug: String
)
