package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PosterDTO(
    val previewUrl: String,
    val url: String
)
