package com.example.dickysuryo.moviecatalogue.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dickysuryo.moviecatalogue.Adapter.ListAdapter_MovieNewest;
import com.example.dickysuryo.moviecatalogue.Constant;
import com.example.dickysuryo.moviecatalogue.MainActivity;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.Network.RetrofitClientInstance;
import com.example.dickysuryo.moviecatalogue.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycle_view;
    RetrofitClientInstance retrofitClientInstance;
    LottieAnimationView animationView;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        recycle_view = (RecyclerView) rootView.findViewById(R.id.recycle_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(linearLayoutManager);

        animationView = (LottieAnimationView) rootView.findViewById(R.id.progressAnimationView);


        if (savedInstanceState != null) {
            Constant.items = savedInstanceState.getParcelableArrayList("movies");
            ListAdapter_MovieNewest customAdapter = new ListAdapter_MovieNewest(getContext(), Constant.items);
            recycle_view.setAdapter(customAdapter);
          stopAnimateLottie();
        } else {
            loadMovies();
        }
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) Constant.items);
    }

    private void loadMovies() {
        animationView.playAnimation();
        Call<MovieNewest_Model> call;
        call = retrofitClientInstance.getInstance().getApiInterface().getAllNewestMovie("44c09582cf533adac2fed1dccbc47c8b");
        call.enqueue(new Callback<MovieNewest_Model>() {
            @Override
            public void onResponse(Call<MovieNewest_Model> call, Response<MovieNewest_Model> response) {
                final List<MovieNewest_Model.Result> items = response.body().getResults();
                ListAdapter_MovieNewest customAdapter = new ListAdapter_MovieNewest(getContext(), items);
                recycle_view.setAdapter(customAdapter);
                Constant.items = items;
                stopAnimateLottie();
            }

            @Override
            public void onFailure(Call<MovieNewest_Model> call, Throwable t) {
                Log.e(TAG, t.toString());

            }
        });
    }
    public void stopAnimateLottie(){
        animationView.clearAnimation();
        animationView.setScaleX(0);
        animationView.setScaleY(0);
    }

}

