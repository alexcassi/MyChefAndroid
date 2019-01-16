package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button avviaSchermata = (Button)findViewById(R.id.bottom);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"schermata avviata da bottone" , Toast.LENGTH_LONG).show();
                Intent secondIntent = new Intent(Login.this,SignUp.class);
                secondIntent.putExtra("","");
                startActivity(secondIntent);
            }
        });


    }
}
