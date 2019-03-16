package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import entità.Ricetta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.ServerUtility;

public class Dettagli_ricetta extends Activity {

    Button avviaSchermataEdit;
    Button avviaSchermataRicette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_ricetta);

        avviaSchermataEdit = (Button)findViewById(R.id.edit);
        avviaSchermataEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this,modifica_ricette.class);
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

        Integer id = getIntent().getIntExtra("id_ricetta",-1);
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
            }

            @Override
            public void onFailure(Call<Ricetta> call, Throwable t) {
                Toast.makeText(Dettagli_ricetta.this,
                        "Errore interno. Riprovare. Se persiste contattarci", Toast.LENGTH_LONG).show();
            }
        });

    }
}
