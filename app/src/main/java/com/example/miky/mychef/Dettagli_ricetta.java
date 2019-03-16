package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dettagli_ricetta extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_ricetta);

        Button avviaSchermataEdit = (Button)findViewById(R.id.edit);
        avviaSchermataEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dettagli_ricetta.this,modifica_ricette.class);
                startActivity(intent);
            }
        });
    }
}
