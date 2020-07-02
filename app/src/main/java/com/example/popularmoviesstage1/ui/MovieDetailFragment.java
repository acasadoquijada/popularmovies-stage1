package com.example.popularmoviesstage1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.popularmoviesstage1.R;
import com.example.popularmoviesstage1.databinding.MovieDetailFragmentBinding;
import com.example.popularmoviesstage1.movie.Movie;
import com.example.popularmoviesstage1.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

public class MovieDetailFragment extends Fragment {

    private MovieDetailFragmentBinding mBinding;
    private MovieViewModel mViewModel;

    public MovieDetailFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment,container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createViewModel();

        populateUI();
    }

    private int getPosition(){

        if(getArguments() != null){
            MovieDetailFragmentArgs args = MovieDetailFragmentArgs.fromBundle(getArguments());
            return args.getPosition();
        }
        return 0;
    }

    private void createViewModel(){
        mViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);
    }

    private void populateUI(){

        Movie movie = mViewModel.getMovie(getPosition());

        setPosterPath(movie.getPoster_path());

        setOriginalTitle(movie.getOriginal_title());

        setOverview(movie.getOverview());

        setReleaseDate(movie.getRelease_date());

        setVoteAverage(movie.getVote_average());
    }

    private void setPosterPath(String path){
        Picasso.get().load(path).into(mBinding.moviePoster);
    }

    private void setOriginalTitle(String title){
        mBinding.title.setText(title);
    }

    private void setOverview(String overview){
        mBinding.overview.setText(overview);

    }

    private void setReleaseDate(String releaseDate){
        mBinding.releaseDate.setText(requireContext().getText(R.string.release_date_helper));
        mBinding.releaseDate.append(" " + releaseDate);
    }

    private void setVoteAverage(double voteAverage){

        mBinding.voteAverage.setText(requireContext().getText(R.string.vote_average_help_text);
        mBinding.voteAverage.append(voteAverage + "/10");
    }
}
