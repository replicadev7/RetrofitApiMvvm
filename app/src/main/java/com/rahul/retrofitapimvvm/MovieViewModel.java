package com.rahul.retrofitapimvvm;

import static com.rahul.retrofitapimvvm.Glob.API_KEY;
import static com.rahul.retrofitapimvvm.Glob.ARTICLE_QUERY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rahul.retrofitapimvvm.model.MovieResponse;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository articleRepository;
    private LiveData<MovieResponse> articleResponseLiveData;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new MovieRepository();
        this.articleResponseLiveData = articleRepository.getMovieArticles(ARTICLE_QUERY, API_KEY);
    }

    public LiveData<MovieResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}