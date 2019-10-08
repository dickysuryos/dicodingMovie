package com.example.dickysuryo.moviecatalogue.Network;

import com.example.dickysuryo.moviecatalogue.Model.DetailMovie_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.Model.MoviePopular_Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie?sort_by=popularity.desc")
    Call<MoviePopular_Model> getAllPopularMovie(@Query("api_key")String Api);

    @GET("discover/movie?with_genres=18&primary_release_year=2014")
    Call<MovieNewest_Model> getAllNewestMovie(@Query("api_key")String Api);

    @GET("movie/{id}")
    Call<DetailMovie_Model> getDetailMovie(@Path("id") int id, @Query("api_key") String api_key);
}
