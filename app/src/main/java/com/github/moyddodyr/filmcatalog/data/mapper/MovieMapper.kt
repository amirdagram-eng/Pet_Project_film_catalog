package com.github.moyddodyr.filmcatalog.data.mapper
import com.github.moyddodyr.filmcatalog.data.dto.MovieDto
import com.github.moyddodyr.filmcatalog.domain.model.Movie

fun MovieDto.toDomainModel(): Movie{
    return Movie(
        id = id,
        title = name,
        posterUrl = poster?.toString(),
        ratingKp = rating as Double?,
        genres = genres,
        year = year,
        description = description,
    )
}
