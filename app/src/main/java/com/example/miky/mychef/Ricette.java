package com.example.miky.mychef;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
    ImageButton aggiungi_ricetta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricette);

        aggiungi_ricetta = findViewById(R.id.add_ricettaBT);
        aggiungi_ricetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ricette.this,Aggiungi_ricetta.class);
                startActivity(intent);
            }
        });

        final Call<List<Ricetta>> call_ricette = ServerUtility.getApiService()
                .getRicette(Sessione.getSessionId(getApplicationContext()));
        call_ricette.enqueue(new Callback<List<Ricetta>>() {
            @Override
            public void onResponse(Call<List<Ricetta>> call, Response<List<Ricetta>> response) {
                lista_ricette = response.body();

                listView = findViewById(R.id.ricetteLV);
                ricetteArrayAdapter = new RicetteArrayAdapter(Ricette.this,
                        R.layout.ricette_single_row);
                ricetteArrayAdapter.addAll(lista_ricette);
                listView.setAdapter(ricetteArrayAdapter);

                try {
                    adapterRicetta.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adapterRicetta.updateOrCreateRicette(lista_ricette,
                        Sessione.getSessionId(getApplicationContext()));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Ricetta r = ricetteArrayAdapter.getItem(i);
                        Intent dettagli = new Intent(Ricette.this, Dettagli_ricetta.class);
                        dettagli.putExtra("id_ricetta",r.getId());
                        startActivity(dettagli);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Ricetta>> call, Throwable t) {
                Toast.makeText(Ricette.this,
                        "Errore interno. Riprovare. Se persiste contattarci", Toast.LENGTH_LONG).show();
            }
        });
    }

}