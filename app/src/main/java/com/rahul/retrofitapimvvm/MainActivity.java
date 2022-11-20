package com.rahul.retrofitapimvvm;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahul.retrofitapimvvm.databinding.ActivityMainBinding;
import com.rahul.retrofitapimvvm.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private ArrayList<Movie> articleArrayList;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        articleArrayList = new ArrayList<>();

        initialization();

        getMovieArticles();

    }

    private void initialization() {
        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setHasFixedSize(true);
        adapter = new MovieArticleAdapter(MainActivity.this, articleArrayList);
        binding.recyclerView.setAdapter(adapter);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    private void getMovieArticles() {
        movieViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                binding.progressBar.setVisibility(View.GONE);
                List<Movie> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }
        });
    }
}