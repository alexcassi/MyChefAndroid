package com.example.miky.mychef;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import endpoint.MyApiEndpointInterface;
import entità.Chef;
import entità.Utente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class TEST_RETROFIT extends Activity {

    public static final String BASE_URL = "http://10.0.2.2:8080/";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__retrofit);
        tv = findViewById(R.id.tv1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);

        Call<List<Chef>> call = apiService.getChefs();

        call.enqueue(new Callback<List<Chef>>() {
            @Override
            public void onResponse(Call<List<Chef>> call, Response<List<Chef>> response) {
                int statusCode = response.code();
                List<Chef> lista_chef = response.body();
                Log.w("la risposta",new Gson().toJson(response));
                Log.w("la risposta", response.toString());

                StringBuilder builder = new StringBuilder();
                for (Chef c: lista_chef) {
                    builder.append(c.getEmail());builder.append(", ");
                    builder.append(c.getPassword());builder.append(", ");
                    builder.append(c.getNome());builder.append(", ");
                    builder.append(c.getCognome());builder.append(", ");
                    builder.append(c.getLuogo_lavoro());builder.append(", ");
                    builder.append(c.getImmagine_profilo());builder.append(", ");
                    builder.append("\n");
                }

                tv.setText(builder.toString());

            }
            @Override
            public void onFailure(Call<List<Chef>> call, Throwable t) {
                Log.w("errore", "errore");
            }
        });

        Button login = findViewById(R.id.loginbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Chef> call_utente = apiService.loginChef("marco95.cl@hotmail.it", "123123");
                call_utente.enqueue(new Callback<Chef>() {
                    @Override
                    public void onResponse(Call<Chef> call, Response<Chef> response) {
                        int statusCode = response.code();
                        Chef u = response.body();
                        Log.w("la risposta", new Gson().toJson(response));
                        Log.w("la risposta", response.toString());

                        StringBuilder builder = new StringBuilder();

                        builder.append(u.getEmail());
                        builder.append(", ");
                        builder.append(u.getPassword());
                        builder.append(", ");
                        builder.append(u.getNome());
                        builder.append(", ");
                        builder.append(u.getCognome());
                        builder.append(", ");
                        builder.append(u.getLuogo_lavoro());
                        builder.append(", ");
                        builder.append(u.getImmagine_profilo());
                        builder.append("\n");

                        tv.setText(builder.toString());
                    }
                        @Override
                        public void onFailure(Call<Chef> call, Throwable t) {
                            tv.setText("Email o password errati");
                        }
                    });

            }
        });

        Button signup = findViewById(R.id.signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Call<String> risposta = apiService.signupChef("ANDROID_TEST@TEST.it","testtest",
                        "ANDREW","ANDROID","ANDROIDCITY");
               risposta.enqueue(new Callback<String>() {
                   @Override
                   public void onResponse(Call<String> call, Response<String> response) {
                       Log.w("la risposta", new Gson().toJson(response));
                       Log.w("la risposta", response.toString());
                       String messaggio = response.body().toString();
                       Log.w("MESSAGGIO: ",messaggio);
                   }

                   @Override
                   public void onFailure(Call<String> call, Throwable t) {
                       Log.w("ERRORE","ERRORE");
                   }
               });
            }
        });

    }
}