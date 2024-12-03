package com.example.myapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(emptyList())
    val series = MutableStateFlow<List<Serie>>(emptyList())
    val acteurs = MutableStateFlow<List<Acteur>>(emptyList())
    val detailMovie = MutableStateFlow<DetailMovie?>(null)
    val detailSerie = MutableStateFlow<DetailSerie?>(null)
    val detailActor = MutableStateFlow<DetailActor?>(null)

    val apikey = "cb36a049f2b010ae388fdedc34bd25eb"
    val language = "fr"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovies(motCle: String) {
        viewModelScope.launch {
            try {
                movies.value = service.getFilmsParMotCle(apikey, motCle).results
            } catch (e: Exception) {
                println("Erreur lors de la recherche de films : ${e.message}")
            }
        }
    }

    fun searchSeries(motCle: String) {
        viewModelScope.launch {
            try {
                series.value = service.getSeriesParMotCle(apikey, motCle).results
            } catch (e: Exception) {
                println("Erreur lors de la recherche de series : ${e.message}")
            }
        }
    }

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                detailMovie.value = service.getFilmDetail(movieId, apikey, language)
            } catch (e: Exception) {
                println("Erreur lors de la récupération des détails : ${e.message}")
            }
        }
    }

    fun serieDetail(serieId: Int) {
        viewModelScope.launch {
            try {
                detailSerie.value = service.getSerieDetail(serieId, apikey, language)
            } catch (e: Exception) {
                println("Erreur lors de la récupération des détails : ${e.message}")
            }
        }
    }

    fun actorDetail(actorId: Int) {
        viewModelScope.launch {
            try {
                detailActor.value = service.getActorDetail(actorId, apikey, language)
            } catch (e: Exception) {
                println("Erreur lors de la récupération des détails : ${e.message}")
            }
        }
    }

    fun weekMovies() {
        viewModelScope.launch {
            try {
                movies.value = service.getFilmsSemaine(apikey).results
            } catch (e: Exception) {
                println("Erreur lors de la récupération des films de la semaine : ${e.message}")
            }
        }
    }

    fun weekSeries() {
        viewModelScope.launch {
            try {
                series.value = service.getSeriesSemaine(apikey).results
            } catch (e: Exception) {
                println("Erreur lors de la récupération des séries de la semaine : ${e.message}")
            }
        }
    }

    fun popularActors() {
        viewModelScope.launch {
            try {
                acteurs.value = service.getActeursPopulaires(apikey).results
            } catch (e: Exception) {
                println("Erreur lors de la récupération des acteurs populaires : ${e.message}")
            }
        }
    }

    fun searchActors(motCle: String) {
        viewModelScope.launch {
            try {
                acteurs.value = service.getActeursParMotCle(apikey, motCle).results
            } catch (e: Exception) {
                println("Erreur lors de la recherche des acteurs : ${e.message}")
            }
        }
    }

    

}
