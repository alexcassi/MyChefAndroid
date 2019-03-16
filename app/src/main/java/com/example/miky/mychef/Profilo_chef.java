package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profilo_chef extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_chef);

        Button avviaSchermata = (Button)findViewById(R.id.home);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_chef.this, Home.class);
                startActivity(secondIntent);
            }
        });

        Button avviaSchermata1 = (Button)findViewById(R.id.ricette);
        avviaSchermata1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_chef.this, Dettagli_ricetta.class);
                startActivity(secondIntent);
            }
        });
    }
}
