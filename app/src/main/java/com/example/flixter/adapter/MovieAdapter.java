package com.example.flixter.adapter;


import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the Holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       // Log.d("MovieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through Holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // Log.d("MovieAdapter","onBindViewHolder" + position);
        // Get the movie at the passed in position
        Movie movie = movies.get(position);
        // Bind the movie data into the View Holder
        holder.bind(movie);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            double rate = movie.getVoteAverage();
            float rating = (float)rate;
            rating = rating/2;
            ratingBar.setRating(rating);
            String  imageUrl = movie.getPosterPath();
            // if phone is an landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // the imageUrl = back drop image
                imageUrl = movie.getBackdropPath();
            }
            if (movie.getVoteAverage() > 5.0){
                imageUrl = movie.getBackdropPath();
            }
           // else {
                // else imageUrl = poster image
               // imageUrl = movie.getPosterPath();
            //}
           // Glide.with(context).load(imageUrl).into(ivPoster);
            Glide.with(context).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.popcorn)).into(ivPoster);
        }
    }
}
