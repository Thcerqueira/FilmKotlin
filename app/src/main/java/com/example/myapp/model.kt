package com.example.myapp
data class TmdbResult<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val adult: Boolean,
    val backdrop_path: Any,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class Serie(
    val adult: Boolean,
    val backdrop_path: Any,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class Acteur(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profile_path: String,
)


