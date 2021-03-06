package com.example.popularmoviesstage1.movie;

import android.content.Context;
import android.content.pm.ModuleInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Adapter class in charge of presents the different movies to the user and handle its interaction
 * with them
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private final GridItemClickListener gridItemClickListener;
    private List<Movie> mMovies;

    /**
     * Constructor method
     * @param listener onClickListener
     * @param m ArrayList of movies
     */

    public MovieAdapter(GridItemClickListener listener,List<Movie> m){
        gridItemClickListener = listener;
        mMovies = m;
    }

    public MovieAdapter(GridItemClickListener listener){
        gridItemClickListener = listener;
    }

    public void setMovies(List<Movie> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    /**
     * Creates a ViewHolder inflating the layout from an xml file
     * @param viewGroup View group
     * @param viewType type of view
     * @return a movie ViewHolder object
     */

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_image;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new MovieViewHolder(view);
    }

    /**
     * Interface containing onClick method
     */

    public interface GridItemClickListener {
        void onGridItemClick(int clickedItemIndex);
    }

    /**
     * Binds a MovieViewHolder object
     * @param holder MovieViewHolder object
     * @param position of the view clicked
     */

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position).getPoster_path());
    }

    @Override
    public int getItemCount() {
        if(mMovies != null)
            return mMovies.size();

        return 0;
    }


    /**
     * Class that represents a MovieViewHolder
     */

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView itemView;

        /**
         * Constructor method that sets:
         * * itemView (a ImageView) using findViewById
         * * a setOnClickListener object
         * @param view MovieViewHolder object
         */
        MovieViewHolder(@NonNull View view) {
            super(view);

            itemView = view.findViewById(R.id.image_iv);

            itemView.setOnClickListener(this);

        }

        /**
         * Sets the movie's poster into the ImageView
         * @param path to movie's poster
         */
        void bind (String path){
            Picasso.get().load(path).into(itemView);
        }


        /**
         * onClick method triggered when the MovieViewHolder is clicked
         * @param v view clicked
         */
        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            gridItemClickListener.onGridItemClick(pos);

        }
    }


}