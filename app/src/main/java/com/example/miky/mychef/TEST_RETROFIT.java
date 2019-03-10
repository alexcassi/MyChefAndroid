package com.example.miky.mychef;

import android.app.Activity;
import android.os.Bundle;

import endpoint.MyApiEndpointInterface;
import entità.Chef;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class TEST_RETROFIT extends Activity {

    public static final String BASE_URL = "http://http://10.0.2.2:8080/web-mychef/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);
        Call<Chef> call = apiService.getChefs();
        call.enqueue(new Callback<Chef>() {
            @Override
            public void onResponse(Call<Chef> call, Response<Chef> response) {
                int statusCode = response.code();
                Chef chef = response.body();
            }
            @Override
            public void onFailure(Call<Chef> call, Throwable t) {
                // Log error richiesta fallita
            }
        });
    }
}