package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.domain.movies.model.Movie;
import com.geekbrains.team.domain.tv.model.TVShow;
import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.SmallCardItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieSmallCardAdapter extends RecyclerView.Adapter<MovieSmallCardAdapter.SmallCardHolder> {
    private List<Movie> movies = new ArrayList<>();
    private List<TVShow> tvShows = new ArrayList<>();

    public MovieSmallCardAdapter() {
    }

    public void setMovies(List<Movie> data) {
        movies.clear();
        movies.addAll(data);
    }

    public void setTvShows(List<TVShow> data) {
        tvShows.clear();
        tvShows.addAll(data);
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @NonNull
    @Override
    public SmallCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SmallCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.small_card_item, parent, false);
        return new SmallCardHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SmallCardHolder holder, int position) {
        if (!movies.isEmpty())
            holder.bindMovie(movies.get(position));
        else
            holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class SmallCardHolder extends RecyclerView.ViewHolder {

        SmallCardItemBinding binding;

        SmallCardHolder(SmallCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindMovie(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindTVShow(TVShow tvShow) {
            binding.setTvShow(tvShow);
            binding.executePendingBindings();
        }
    }
}
