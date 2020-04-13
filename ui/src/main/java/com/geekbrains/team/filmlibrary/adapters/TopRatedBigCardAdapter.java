package com.geekbrains.team.filmlibrary.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
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
import com.squareup.picasso.Picasso;

/**
 * адаптер для верхнего вьюпейджера на главной
 */
public class TopRatedBigCardAdapter extends RecyclerView.Adapter<TopRatedBigCardAdapter.TopRatedBigCardHolder> {
    private MovieView movie;

    public TopRatedBigCardAdapter() {
    }

    public void setMovie(MovieView data) {
        movie = data;
    }

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @NonNull
    @Override
    public TopRatedBigCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BigCardItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.big_card_item, parent, false);
        return new TopRatedBigCardHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedBigCardHolder holder, int position) {
        holder.bindMovie(movie);
        holder.play.setOnClickListener(v -> v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getTrailer()))));
        Picasso.get().load(movie.getImages().get(position)).into(holder.backDrop);
    }

    @Override
    public int getItemCount() {
        return movie.getImages().size();
    }

    class TopRatedBigCardHolder extends RecyclerView.ViewHolder {
        private ImageButton play;
        private ImageView backDrop;

        BigCardItemBinding binding;

        TopRatedBigCardHolder(BigCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            play = binding.getRoot().findViewById(R.id.play_btn);
            backDrop = binding.getRoot().findViewById(R.id.title_iv);
        }

        void bindMovie(MovieView movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
