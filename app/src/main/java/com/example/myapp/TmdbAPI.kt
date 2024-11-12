package com.example.myapp

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") motcle: String) : TmdbResult

    @GET("discover/movie")
    suspend fun getFilmsSemaine(@Query("api_key") apikey: String, @Query("sort_by") sortBy: String = "release_date.desc"): TmdbResult
}