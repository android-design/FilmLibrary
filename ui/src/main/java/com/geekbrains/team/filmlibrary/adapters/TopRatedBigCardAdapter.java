package com.geekbrains.team.filmlibrary.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.team.filmlibrary.R;
import com.geekbrains.team.filmlibrary.databinding.BigCardItemBinding;
import com.geekbrains.team.filmlibrary.model.MovieView;
import com.squareup.picasso.Picasso;

/**
 * адаптер для верхнего вьюпейджера на главной
 */
public class TopRatedBigCardAdapter extends RecyclerView.Adapter<TopRatedBigCardAdapter.TopRatedBigCardHolder> {
    public MovieView movie;

    private OnItemSelectedListener listener;

    public TopRatedBigCardAdapter() {
    }

    public void attachListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public void setMovies(MovieView data) {
        movie = data;
    }

    @NonNull
    @Override
    public TopRatedBigCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BigCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.big_card_item, parent, false);
        return new TopRatedBigCardHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedBigCardHolder holder, int position) {
        if (movie != null) {
            holder.bindMovie(movie);
            holder.play.setOnClickListener(v -> v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getTrailer()))));
            Picasso.get().load(movie.getImages().get(position)).into(holder.backDrop);
        }
    }

    @Override
    public int getItemCount() {
        if (movie == null) return 0;
        else return movie.getImages().size();
    }

    class TopRatedBigCardHolder extends RecyclerView.ViewHolder {
        BigCardItemBinding binding;
        OnItemSelectedListener listener;
        private ImageButton play;
        private ImageView backDrop;

        TopRatedBigCardHolder(BigCardItemBinding binding, OnItemSelectedListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
            play = binding.getRoot().findViewById(R.id.play_btn);
            backDrop = binding.getRoot().findViewById(R.id.title_iv);
        }

        void bindMovie(MovieView movie) {
            binding.setListener(listener);
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
