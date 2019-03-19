package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import entità.Ricetta;
import preferenze.Sessione;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.MyApiEndpointInterface;
import servizi_web.ServerUtility;
import utilities.Utilities;

public class Aggiungi_ricetta extends FragmentActivity {
    Button button_aggiungi;
    String email_chef;
    MyApiEndpointInterface api_service;
    EditText et_nome;
    EditText et_ingr;
    EditText et_tempo;
    EditText et_prezzo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_ricetta);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_menu,
                new FRAG_MENU_BAR()).commit();

        api_service = ServerUtility.getApiService();
        email_chef = Sessione.getSessionId(getApplicationContext());

        button_aggiungi = (Button)findViewById(R.id.aggiungiBT);
        Utilities.buttonEffect(button_aggiungi);
        button_aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nome = findViewById(R.id.nome_ricetta);
                et_ingr = findViewById(R.id.ingredienti);
                et_tempo = findViewById(R.id.tempo_preparazione);
                et_prezzo = findViewById(R.id.prezzo);

                Call<String> aggiornaCall = api_service.
                        addRicetta(et_nome.getText().toString(),
                                et_ingr.getText().toString(), et_tempo.getText().toString(),
                                Double.valueOf(et_prezzo.getText().toString()), email_chef);
                aggiornaCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String risposta = response.body().toString();
                        Toast.makeText(Aggiungi_ricetta.this,
                                risposta, Toast.LENGTH_LONG).show();
                        if(risposta.equals("ricetta aggiunta")) {
                            Intent intent = new Intent(Aggiungi_ricetta.this, Ricette.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(Aggiungi_ricetta.this,
                                "Errore interno. Riprovare. Se persiste contattarci",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
