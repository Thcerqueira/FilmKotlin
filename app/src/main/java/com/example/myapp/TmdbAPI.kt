package com.example.myapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmsParMotCle(
        @Query("api_key") apikey: String,
        @Query("query") motCle: String
    ): TmdbResult<Movie>

    @GET("search/tv")
    suspend fun getSeriesParMotCle(
        @Query("api_key") apikey: String,
        @Query("query") motCle: String
    ): TmdbResult<Serie>

    @GET("trending/movie/week")
    suspend fun getFilmsSemaine(
        @Query("api_key") apikey: String
    ): TmdbResult<Movie>

    @GET("trending/tv/week")
    suspend fun getSeriesSemaine(
        @Query("api_key") apikey: String
    ): TmdbResult<Serie>

    @GET("person/popular")
    suspend fun getActeursPopulaires(
        @Query("api_key") apikey: String
    ): TmdbResult<Acteur>

    @GET("search/person")
    suspend fun getActeursParMotCle(
        @Query("api_key") apikey: String,
        @Query("query") motCle: String
    ): TmdbResult<Acteur>

    @GET("movie/{movieId}")
    suspend fun getFilmDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): DetailMovie

    @GET("tv/{serieId}")
    suspend fun getSerieDetail(
        @Path("serieId") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): DetailSerie

    @GET("person/{actorId}")
    suspend fun getActorDetail(
        @Path("actorId") serieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): DetailActor



}
