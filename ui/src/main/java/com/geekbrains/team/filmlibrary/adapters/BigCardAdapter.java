package com.geekbrains.team.filmlibrary.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.BigCardItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;

import java.util.ArrayList;
import java.util.List;

public class BigCardAdapter extends RecyclerView.Adapter<BigCardAdapter.BigCardHolder> {
    public List<MovieView> movie = new ArrayList<>();
    public List<TVShowView> tvShow = new ArrayList<>();

    public BigCardAdapter() {
    }

    public void setMovies(MovieView data) {
        movie.clear();
        movie.add(data);
    }

    public void setTVShows(List<TVShowView> data) {
        tvShow.clear();
        tvShow.addAll(data);
    }

    @NonNull
    @Override
    public BigCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BigCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.big_card_item, parent, false);
        return new BigCardHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BigCardHolder holder, int position) {
        String trailer;
        if (!movie.isEmpty()) {
            holder.bindMovie(movie.get(position));
            trailer = movie.get(position).getTrailer();
        } else {
            holder.bindTVShow(tvShow.get(position));
            trailer = tvShow.get(position).getTrailer();
        }

        String finalTrailer = trailer;
        holder.play.setOnClickListener(v -> v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(finalTrailer))));
    }

    @Override
    public int getItemCount() {
        if (!movie.isEmpty())
            return movie.size();
        else
            return tvShow.size();
    }

    static class BigCardHolder extends RecyclerView.ViewHolder {
        BigCardItemBinding binding;
        private ImageButton play;

        BigCardHolder(BigCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            play = binding.getRoot().findViewById(R.id.play_btn);
        }

        void bindMovie(MovieView movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindTVShow(TVShowView movie) {
            binding.setTvShow(movie);
            binding.executePendingBindings();
        }
    }
}