package com.github.moyddodyr.filmcatalog.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryDTO(
    val id: Int,
    val name: String
)
