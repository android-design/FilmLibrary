package com.geekbrains.team.filmlibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.BigCardItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.geekbrains.team.filmlibrary.model.TVShowView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class BigCardAdapter extends RecyclerView.Adapter<BigCardAdapter.BigCardHolder> {
    private List<MovieView> movie = new ArrayList<>();
    private List<TVShowView> tvShow = new ArrayList<>();

    public BigCardAdapter() {}

    public void setNowPlayingMovie(List<MovieView> data) {
        movie.clear();
        movie.addAll(data);
    }

    public void setUpcomingMovie(List<TVShowView> data) {
        tvShow.clear();
        tvShow.addAll(data);
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
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
        if (!movie.isEmpty())
            holder.bindNowPlayingMovie(movie.get(position));
        else
            holder.bindUpcomingMovie(tvShow.get(position));

        holder.play.setOnClickListener(playListener);
    }

    @Override
    public int getItemCount() {
        if (!movie.isEmpty())
            return movie.size();
        else
            return tvShow.size();
    }

    private View.OnClickListener playListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //todo обработка нажания на плэй
        }
    };

    class BigCardHolder extends RecyclerView.ViewHolder {
        private ImageButton play;

        BigCardItemBinding binding;

        BigCardHolder(BigCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            play = binding.getRoot().findViewById(R.id.play_btn);
        }

        void bindNowPlayingMovie(MovieView movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }

        void bindUpcomingMovie(TVShowView movie) {
            binding.setTvShow(movie);
            binding.executePendingBindings();
        }
    }
}