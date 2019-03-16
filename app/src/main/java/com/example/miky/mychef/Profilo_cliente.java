package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profilo_cliente extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo_cliente);

        Button avviaSchermata = (Button)findViewById(R.id.home);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_cliente.this, Home.class);
                startActivity(secondIntent);
            }
        });

        Button avviaSchermata1 = (Button)findViewById(R.id.logout);
        avviaSchermata1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(Profilo_cliente.this,Login.class);
                startActivity(secondIntent);
            }
        });
    }
}
