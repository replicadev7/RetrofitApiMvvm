package com.rahul.retrofitapimvvm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rahul.retrofitapimvvm.model.MovieResponse;
import com.rahul.retrofitapimvvm.retrofit.ApiRequest;
import com.rahul.retrofitapimvvm.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static final String TAG = MovieRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public MovieRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<MovieResponse> getMovieArticles(String query, String key) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}

