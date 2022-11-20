package com.rahul.retrofitapimvvm.retrofit;

import com.rahul.retrofitapimvvm.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("v2/everything/")
    Call<MovieResponse> getMovieArticles(@Query("q") String query, @Query("apikey") String apiKey);

}
