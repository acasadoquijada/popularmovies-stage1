package com.example.popularmoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private int mNumberOfItems;
    private GridItemClickListener gridItemClickListener;
    private ArrayList<Movie> mMovies;

    MovieAdapter(int numberOfItems, GridItemClickListener listener,ArrayList<Movie> m){
        mNumberOfItems = numberOfItems;
        gridItemClickListener = listener;
        mMovies = m;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.film_image;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new MovieViewHolder(view);
    }

    public interface GridItemClickListener {
        void onGridItemClick(int clickedItemIndex);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position).getPoster_path());
    }

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView itemView;

        MovieViewHolder(@NonNull View view) {
            super(view);

            itemView = view.findViewById(R.id.image_iv);

            itemView.setOnClickListener(this);

        }

        void bind (String path){
            //Picasso.get().load("https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg").into(itemView);

            Picasso.get().load(path).into(itemView);
        }


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            gridItemClickListener.onGridItemClick(pos);

        }
    }


}