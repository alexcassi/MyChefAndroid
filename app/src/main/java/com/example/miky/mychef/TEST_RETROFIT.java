package com.example.miky.mychef;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;
import java.util.TreeMap;

import endpoint.MyApiEndpointInterface;
import entità.Chef;
import entità.Cliente;
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
                Call<TreeMap<String,String>> call_utente = apiService.loginChef("g.keplero@astro.com", "astronomo");
                call_utente.enqueue(new Callback<TreeMap<String,String>>() {
                    @Override
                    public void onResponse(Call<TreeMap<String,String>> call, Response<TreeMap<String,String>> response) {
                        int statusCode = response.code();
                        TreeMap<String,String> u = response.body();
                        StringBuilder builder = new StringBuilder();
                        Log.w("la risposta", new Gson().toJson(response));
                        Log.w("la risposta", response.toString());

                        //caso chef
                        if(u.containsKey("luogo_lavoro")){
                            Chef chef = new Chef();
                            chef.setEmail(u.get("email"));
                            chef.setNome(u.get("nome"));
                            chef.setCognome(u.get("cognome"));
                            chef.setPassword(u.get("password"));
                            chef.setLuogo_lavoro(u.get("luogo_lavoro"));
                            chef.setImmagine_profilo(u.get("immagine_profilo"));

                            builder.append(chef.getEmail());
                            builder.append(", ");
                            builder.append(chef.getPassword());
                            builder.append(", ");
                            builder.append(chef.getNome());
                            builder.append(", ");
                            builder.append(chef.getCognome());
                            builder.append(", ");
                            builder.append(chef.getLuogo_lavoro());
                            builder.append(", ");
                            builder.append(chef.getImmagine_profilo());
                            builder.append("\n");
                        } else {
                            //caso cliente
                            if (u.containsKey("indirizzo")) {
                                Cliente cliente = new Cliente();
                                cliente.setEmail(u.get("email"));
                                cliente.setNome(u.get("nome"));
                                cliente.setCognome(u.get("cognome"));
                                cliente.setPassword(u.get("password"));
                                cliente.setProvincia(u.get("provincia"));
                                cliente.setComune(u.get("comune"));
                                cliente.setIndirizzo(u.get("indirizzo"));

                                builder.append(cliente.getEmail());
                                builder.append(", ");
                                builder.append(cliente.getPassword());
                                builder.append(", ");
                                builder.append(cliente.getNome());
                                builder.append(", ");
                                builder.append(cliente.getCognome());
                                builder.append(", ");
                                builder.append(cliente.getProvincia());
                                builder.append(", ");
                                builder.append(cliente.getComune());
                                builder.append(", ");
                                builder.append(cliente.getIndirizzo());
                                builder.append("\n");
                            } else {
                                //caso login fallito
                                builder.append(u.get("messaggio"));
                            }
                        }

                        tv.setText(builder.toString());
                    }
                        @Override
                        public void onFailure(Call<TreeMap<String,String>> call, Throwable t) {
                            tv.setText("Errore interno. Riprovare. Se persiste contattarci");
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