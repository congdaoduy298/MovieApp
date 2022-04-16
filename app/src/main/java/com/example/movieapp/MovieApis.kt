package com.example.movieapp
//import com.example.movieapp.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {

    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp


    @GET("movie/top_rated")
    suspend fun listTopRatedMovie(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): MovieResp
}