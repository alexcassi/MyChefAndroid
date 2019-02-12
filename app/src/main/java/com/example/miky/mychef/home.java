package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Button avviaSchermata = (Button)findViewById(R.id.login);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(home.this,Login.class);
                startActivity(secondIntent);
            }
        });



        Button avviaSchermata1 = (Button)findViewById(R.id.signup);
        avviaSchermata1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(home.this,SignUp.class);
                startActivity(secondIntent);
            }
        });
    }
}
