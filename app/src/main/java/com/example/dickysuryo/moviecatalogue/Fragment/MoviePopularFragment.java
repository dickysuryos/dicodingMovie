package com.example.dickysuryo.moviecatalogue.Fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recycle_view = rootView.findViewById(R.id.recycle_view);
         linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(linearLayoutManager);
        animationView = rootView.findViewById(R.id.progressAnimationView);

         fetch_data();

         if (savedInstanceState != null) {
             items = savedInstanceState.getParcelableArrayList(Constant.KEY_LIST_POPULAR);
             ListAdapter_Movie customAdapter = new ListAdapter_Movie(getContext(),items);
            recycle_view.setAdapter(customAdapter);
            stopAnimateLottie();
         } else {
             fetch_data();
         }


        return rootView;
    }


    public void fetch_data(){
        animationView.playAnimation();
        call = retrofitClientInstance.getInstance().getApiInterface().getAllPopularMovie("44c09582cf533adac2fed1dccbc47c8b");
        call.enqueue(new Callback<MoviePopular_Model>() {
            @Override
            public void onResponse(Call<MoviePopular_Model> call, Response<MoviePopular_Model> response) {
                 items = response.body().getResults();
                ListAdapter_Movie customAdapter = new ListAdapter_Movie(getContext(),items);
                recycle_view.setAdapter(customAdapter);
              stopAnimateLottie();
            }

            @Override
            public void onFailure(Call<MoviePopular_Model> call, Throwable t) {
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
