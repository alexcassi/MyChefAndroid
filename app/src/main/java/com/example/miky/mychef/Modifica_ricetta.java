package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import entità.Ricetta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.MyApiEndpointInterface;
import servizi_web.ServerUtility;

public class Modifica_ricetta extends Activity {

    Integer id;
    Button button_aggiorna;
    MyApiEndpointInterface api_service;
    EditText et_nome;
    EditText et_ingr;
    EditText et_tempo;
    EditText et_prezzo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_ricetta);

        api_service = ServerUtility.getApiService();
        id = getIntent().getIntExtra("id_ricetta",-1);

        button_aggiorna = (Button)findViewById(R.id.aggiorna);
        button_aggiorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nome = findViewById(R.id.nome_ricetta);
                et_ingr = findViewById(R.id.ingredienti);
                et_tempo = findViewById(R.id.tempo_preparazione);
                et_prezzo = findViewById(R.id.prezzo);

                Call<String> aggiornaCall = api_service.
                        updateRicetta(et_nome.getText().toString(),
                                et_ingr.getText().toString(), et_tempo.getText().toString(),
                                Double.valueOf(et_prezzo.getText().toString()), Integer.valueOf(id));
                aggiornaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(Modifica_ricetta.this,
                                "Ricetta aggiornata", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Modifica_ricetta.this,Dettagli_ricetta.class);
                        intent.putExtra("id_ricetta",id);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Modifica_ricetta.this,
                                "Errore interno. Riprovare. Se persiste contattarci", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Call<Ricetta> ricettaCall = api_service.getRicetta(id);
        ricettaCall.enqueue(new Callback<Ricetta>() {
            @Override
            public void onResponse(Call<Ricetta> call, Response<Ricetta> response) {
                et_nome = findViewById(R.id.nome_ricetta);
                et_ingr = findViewById(R.id.ingredienti);
                et_tempo = findViewById(R.id.tempo_preparazione);
                et_prezzo = findViewById(R.id.prezzo);

                et_nome.setText(response.body().getNome_ricetta());
                et_ingr.setText(response.body().getIngredienti());
                et_tempo.setText(response.body().getTempo_preparazione());
                et_prezzo.setText(response.body().getPrezzo().toString());
            }

            @Override
            public void onFailure(Call<Ricetta> call, Throwable t) {
                Toast.makeText(Modifica_ricetta.this,
                        "Errore interno. Riprovare. Se persiste contattarci", Toast.LENGTH_LONG).show();
            }
        });

    }
}
