package com.rahul.retrofitapimvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahul.retrofitapimvvm.databinding.ItemListBinding;
import com.rahul.retrofitapimvvm.model.Movie;

import java.util.ArrayList;

public class MovieArticleAdapter extends RecyclerView.Adapter<MovieArticleAdapter.ViewHolder> {

    private Context context;
    ArrayList<Movie> articleArrayList;

    public MovieArticleAdapter(Context context, ArrayList<Movie> articleArrayList) {
        this.context = context;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public MovieArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemListBinding itemBinding = ItemListBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieArticleAdapter.ViewHolder viewHolder, int i) {
        Movie article = articleArrayList.get(i);
        viewHolder.binding.tvTitle.setText(article.getTitle());
        viewHolder.binding.tvAuthorAndPublishedAt.setText("-" + article.getAuthor() + " | " + "Published At: " + article.getPublishedAt());
        viewHolder.binding.tvDescription.setText(article.getDescription());
        Glide.with(context).load(article.getUrlToImage()).into(viewHolder.binding.imgViewCover);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemListBinding binding;

        public ViewHolder(ItemListBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

        }
    }
}