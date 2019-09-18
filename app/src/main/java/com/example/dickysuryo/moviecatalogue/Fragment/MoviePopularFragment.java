package com.example.dickysuryo.moviecatalogue.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dickysuryo.moviecatalogue.Adapter.ListAdapter_Movie;
import com.example.dickysuryo.moviecatalogue.Constant;
import com.example.dickysuryo.moviecatalogue.MainActivity;
import com.example.dickysuryo.moviecatalogue.Model.DetailPopular_Model;
import com.example.dickysuryo.moviecatalogue.Model.MoviePopular_Model;
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
public class MoviePopularFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycle_view;
    RetrofitClientInstance retrofitClientInstance;
    LottieAnimationView animationView;
    LinearLayoutManager linearLayoutManager;
    Parcelable list;
    List<DetailPopular_Model> items;
    Call<MoviePopular_Model> call;
    public MoviePopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constant.KEY_LIST_POPULAR, (ArrayList<? extends Parcelable>) items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_popular, container, false);
        recycle_view = (RecyclerView) rootView.findViewById(R.id.recycle_view);
         linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(linearLayoutManager);
        animationView = (LottieAnimationView)rootView.findViewById(R.id.progressAnimationView);
        animationView.clearAnimation();
        animationView.setScaleX(0);
        animationView.setScaleY(0);
         fetch_data();



        return rootView;
    }


    public void fetch_data(){
        call = retrofitClientInstance.getInstance().getApiInterface().getAllPopularMovie("44c09582cf533adac2fed1dccbc47c8b");
        call.enqueue(new Callback<MoviePopular_Model>() {
            @Override
            public void onResponse(Call<MoviePopular_Model> call, Response<MoviePopular_Model> response) {
                 items = response.body().getResults();
                ListAdapter_Movie customAdapter = new ListAdapter_Movie(getContext(),items);
                recycle_view.setAdapter(customAdapter);
                animationView.clearAnimation();
                animationView.setScaleX(0);
                animationView.setScaleY(0);
            }

            @Override
            public void onFailure(Call<MoviePopular_Model> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
