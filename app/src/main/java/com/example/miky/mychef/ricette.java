package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ricette extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricette);

        Button avviaSchermata = (Button)findViewById(R.id.edit);
        avviaSchermata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(ricette.this,modifica_ricette.class);
                startActivity(secondIntent);
            }
        });
    }
}
