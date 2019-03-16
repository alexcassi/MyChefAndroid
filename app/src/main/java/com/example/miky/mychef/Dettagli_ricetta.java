package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import database.DbAdapterRicetta;
import entità.Ricetta;
import preferenze.Sessione;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.ServerUtility;

public class Dettagli_ricetta extends Activity {

    DbAdapterRicetta adapterRicetta = new DbAdapterRicetta(this);
    Integer id;
    Button avviaSchermataEdit;
    Button avviaSchermataRicette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_ricetta);

        id = getIntent().getIntExtra("id_ricetta",-1);

        avviaSchermataEdit = (Button)findViewById(R.id.edit);
        avviaSchermataEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this, Modifica_ricetta.class);
                intent.putExtra("id_ricetta",id);
                startActivity(intent);
            }
        });
        avviaSchermataRicette = (Button)findViewById(R.id.ricette);
        avviaSchermataRicette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this,Ricette.class);
                startActivity(intent);
            }
        });

        Call<Ricetta> ricettaCall = ServerUtility.getApiService()
                .getRicetta(id);
        ricettaCall.enqueue(new Callback<Ricetta>() {
            @Override
            public void onResponse(Call<Ricetta> call, Response<Ricetta> response) {
                TextView tv_nome = findViewById(R.id.nome);
                tv_nome.append("\n"+response.body().getNome_ricetta());
                TextView tv_ingr = findViewById(R.id.ingredienti);
                tv_ingr.append("\n"+response.body().getIngredienti());
                TextView tv_tempo = findViewById(R.id.tempo);
                tv_tempo.append("\n"+response.body().getTempo_preparazione() + " min");
                TextView tv_prezzo = findViewById(R.id.prezzo);
                tv_prezzo.append("\n"+response.body().getPrezzo() + " €");

                try {
                    adapterRicetta.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(!(adapterRicetta.updateRicetta(response.body()))){
                    adapterRicetta.createRicetta(response.body(), Sessione.
                            getSessionId(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<Ricetta> call, Throwable t) {
                Toast.makeText(Dettagli_ricetta.this,
                        "Errore interno. Riprovare. Se persiste contattarci",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
