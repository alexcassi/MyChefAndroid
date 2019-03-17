package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button avviaSchermata = (Button)findViewById(R.id.signup);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(SignUp.this,Login.class);
                startActivity(secondIntent);
            }
        });

    }

    public void mostraCampiChef(View view){
        RelativeLayout chef_buttons = findViewById(R.id.gruppoChef);
        RelativeLayout cliente_buttons = findViewById(R.id.gruppoCliente);
        cliente_buttons.setVisibility(View.GONE);
        chef_buttons.setVisibility(View.VISIBLE);
    }
    public void mostraCampiCliente(View view){
        RelativeLayout chef_buttons = findViewById(R.id.gruppoChef);
        RelativeLayout cliente_buttons = findViewById(R.id.gruppoCliente);
        cliente_buttons.setVisibility(View.VISIBLE);
        chef_buttons.setVisibility(View.GONE);
    }
}
