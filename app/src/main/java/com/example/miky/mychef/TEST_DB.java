package com.example.miky.mychef;

import android.app.Activity;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

import database.DbAdapter;
import entità.Chef;

public class TEST_DB extends Activity {

    private DbAdapter dbHelper;
    private Cursor cursor;

    TextView demoTextView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__db);

        demoTextView = (TextView)findViewById(R.id.demoTextView);
        button = (Button)findViewById(R.id.button);


        dbHelper = new DbAdapter(this);
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // CREAZIONE CHEF NEL DB
        //dbHelper.createChef(new Chef("email_Y","Y","Z"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoTextView.setText("");

                cursor = dbHelper.fetchAllChefs();

                while ( cursor.moveToNext() ) {
                    String chefID = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_EMAIL) );
                    String nome = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_NOME) );
                    String cognome = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_COGNOME) );
                    String text ="Email: " + chefID + ", Nome: " + nome + ", Cognome: " + cognome + "\n";
                    demoTextView.setText(demoTextView.getText().toString() + text);
                }
            }
        });
    }
}
