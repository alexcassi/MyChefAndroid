package com.example.miky.mychef;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adapters.RicetteArrayAdapter;
import database.DbAdapterRicetta;
import entità.Chef;
import entità.Ricetta;
import preferenze.Sessione;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.ServerUtility;

public class Ricette extends Activity {

    DbAdapterRicetta adapterRicetta = new DbAdapterRicetta(this);
    RicetteArrayAdapter ricetteArrayAdapter;
    ListView listView;
    List<Ricetta> lista_ricette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricette);


        final Call<List<Ricetta>> call_ricette = ServerUtility.getApiService()
                .getRicette(Sessione.getSessionId(getApplicationContext()));
        call_ricette.enqueue(new Callback<List<Ricetta>>() {
            @Override
            public void onResponse(Call<List<Ricetta>> call, Response<List<Ricetta>> response) {
                lista_ricette = response.body();
                Log.w("RISPOSTA: ", response.body().toString());

                listView = findViewById(R.id.ricetteLV);
                ricetteArrayAdapter = new RicetteArrayAdapter(Ricette.this,R.layout.ricette_single_row);
                ricetteArrayAdapter.addAll(lista_ricette);
                listView.setAdapter(ricetteArrayAdapter);

                try {
                    adapterRicetta.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adapterRicetta.updateOrCreateRicette(lista_ricette,Sessione.getSessionId(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Ricetta>> call, Throwable t) {
                Log.w("ERRORE", "ERRORE");
            }
        });
    }

}