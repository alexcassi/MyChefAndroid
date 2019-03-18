package com.example.miky.mychef;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import utilities.Utilities;

public class Dettagli_ricetta extends Activity {

    DbAdapterRicetta adapterRicetta = new DbAdapterRicetta(this);
    Integer id;
    Button avviaSchermataEdit;
    Button avviaDelete;
    Button avviaSchermataRicette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_ricetta);

        id = getIntent().getIntExtra("id_ricetta",-1);

        avviaSchermataEdit = (Button)findViewById(R.id.edit);
        Utilities.buttonEffect(avviaSchermataEdit);
        avviaSchermataEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this, Modifica_ricetta.class);
                intent.putExtra("id_ricetta",id);
                startActivity(intent);
            }
        });
        avviaSchermataRicette = (Button)findViewById(R.id.ricette);
        Utilities.buttonEffect(avviaSchermataRicette);
        avviaSchermataRicette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this,Ricette.class);
                startActivity(intent);
            }
        });
        avviaDelete = findViewById(R.id.delete);
        Utilities.buttonEffect(avviaDelete);
        avviaDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Dettagli_ricetta.this)
                        .setTitle("Elimina ricetta")
                        .setMessage("Sei sicuro di voler eliminare la ricetta?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Sì", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Call<String> eliminaCall = ServerUtility.getApiService()
                                        .removeRicetta(id);
                                eliminaCall.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if (response.body().toString().equals("ok")){
                                            Toast.makeText(Dettagli_ricetta.this,
                                                    "Ricetta eliminata",
                                                    Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(Dettagli_ricetta.this,
                                                    Ricette.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(Dettagli_ricetta.this,
                                                    "Errore interno. Riprovare. Se persiste contattarci",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Toast.makeText(Dettagli_ricetta.this,
                                                "Errore interno. Riprovare. Se persiste contattarci",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                            }})
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
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
