package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.UpcomingSmallCardItemBinding;
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;

import java.util.ArrayList;
import java.util.List;

public class UpcomingSmallCardAdapter extends RecyclerView.Adapter<UpcomingSmallCardAdapter.UpcomingSmallCardHolder> {
    public List<UpcomingMovieView> movie = new ArrayList<>();
    public List<TVShowView> tvShow = new ArrayList<>();

    private OnItemSelectedListener listener;

    public UpcomingSmallCardAdapter() {
    }

    public void attachListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public void setMovies(List<UpcomingMovieView> data) {
        movie.clear();
        movie.addAll(data);
    }

    public void setTVShows(List<TVShowView> data) {
        tvShow.clear();
        tvShow.addAll(data);
    }

    @NonNull
    @Override
    public UpcomingSmallCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        UpcomingSmallCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.upcoming_small_card_item, parent, false);
        return new UpcomingSmallCardHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingSmallCardHolder holder, int position) {
        if (!movie.isEmpty())
            holder.bindMovie(movie.get(position));
        else
            holder.bindTVShow(tvShow.get(position));
    }

    @Override
    public int getItemCount() {
        if (!movie.isEmpty())
            return movie.size();
        else
            return tvShow.size();
    }

    class UpcomingSmallCardHolder extends RecyclerView.ViewHolder {

        UpcomingSmallCardItemBinding binding;
        OnItemSelectedListener listener;

        UpcomingSmallCardHolder(UpcomingSmallCardItemBinding binding, OnItemSelectedListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        void bindMovie(UpcomingMovieView movie) {
            binding.setListener(listener);
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindTVShow(TVShowView movie) {
            binding.setListener(listener);
            binding.setTvShow(movie);
            binding.executePendingBindings();
        }
    }
}