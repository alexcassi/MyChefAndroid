package com.example.miky.mychef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import database.DbAdapterChef;
import retrofit2.Call;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;

import entità.Chef;
import preferenze.Sessione;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.ServerUtility;

public class Profilo_chef extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_chef);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_menu,
                new FRAG_MENU_BAR()).commit();

        Button avviaSchermataRicette = (Button)findViewById(R.id.ricette);
        avviaSchermataRicette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_chef.this, Ricette.class);
                startActivity(secondIntent);
            }
        });

        Button avviaSchermataEdit = (Button)findViewById(R.id.edit);
        avviaSchermataEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_chef.this, Modifica_profilo_chef.class);
                startActivity(secondIntent);
            }
        });

        Call<Chef> chef_call = ServerUtility.getApiService().getChef(Sessione.getSessionId(getApplicationContext()));
        chef_call.enqueue(new Callback<Chef>() {
            @Override
            public void onResponse(Call<Chef> call, Response<Chef> response) {
                Chef chef = response.body();
                DbAdapterChef adapter_chef = new DbAdapterChef(getApplicationContext());
                try {
                    adapter_chef.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(!adapter_chef.updateChef(chef)){
                    adapter_chef.createChef(chef);
                }

            }

            @Override
            public void onFailure(Call<Chef> call, Throwable t) {

            }
        });

    }
}
