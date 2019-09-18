package com.example.dickysuryo.moviecatalogue;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dickysuryo.moviecatalogue.Adapter.DetailAdapter;
import com.example.dickysuryo.moviecatalogue.Fragment.ProgressDialogFragment;
import com.example.dickysuryo.moviecatalogue.Model.DetailMovie_Model;
import com.example.dickysuryo.moviecatalogue.Model.DetailPopular_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.Network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    ImageView image_header;
    TextView txt_title,txt_description;
    LottieAnimationView animationView;
    ProgressDialogFragment progressDialogFragment;
    int idMovie;
    RetrofitClientInstance retrofitClientInstance;
    Call<MovieNewest_Model> calls;
    private static final String TAG = DetailActivity.class.getSimpleName();
    private RecyclerView recycle_view;
    List<MovieNewest_Model.Result>  model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recycle_view = (RecyclerView)findViewById(R.id.recycle_view);
        animationView = (LottieAnimationView)findViewById(R.id.progressAnimationView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        recycle_view.setLayoutManager(linearLayoutManager);


        Bundle bundle = getIntent().getExtras();
        if (bundle.getParcelable("detailMovieNewest") != null){
            MovieNewest_Model.Result parcel = bundle.getParcelable("detailMovieNewest");
            idMovie = parcel.getId();


        }
        else if (bundle.getParcelable("detailMovie") != null){
            DetailPopular_Model parcel = bundle.getParcelable("detailMovie");
            idMovie = parcel.getId();


        }

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        Fragment prev = getFragmentManager().findFragmentByTag("dialog");


//        progressDialogFragment = new ProgressDialogFragment();
//        progressDialogFragment.newInstane("title").show(getSupportFragmentManager(),"dialog");

        animationView.playAnimation();
        Call<DetailMovie_Model> call;

        call = retrofitClientInstance.getInstance().getApiInterface().getDetailMovie(idMovie,Constant.API_KEY);
        call.enqueue(new Callback<DetailMovie_Model>() {
            @Override
            public void onResponse(Call<DetailMovie_Model> call, Response<DetailMovie_Model> response) {
//                final List<DetailMovie_Model> items = response.body();

                DetailAdapter customAdapter = new DetailAdapter(DetailActivity.this,response.body());
                recycle_view.setAdapter(customAdapter);
                animationView.clearAnimation();
                animationView.setScaleX(0);
                animationView.setScaleY(0);

            }

            @Override
            public void onFailure(Call<DetailMovie_Model> call, Throwable t) {
                Log.e(TAG, t.toString());



            }
        });



    }



}
