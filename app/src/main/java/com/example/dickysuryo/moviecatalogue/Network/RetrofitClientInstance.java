package com.example.dickysuryo.moviecatalogue.Network;

import com.example.dickysuryo.moviecatalogue.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static RetrofitClientInstance instance = null;
    private static Retrofit retrofit;
    private ApiInterface apiInterface;

    private RetrofitClientInstance() {

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RetrofitClientInstance getInstance() {
        if (instance == null)
            instance = new RetrofitClientInstance();

        return instance;

    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

}

