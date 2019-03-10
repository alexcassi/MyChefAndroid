package com.example.miky.mychef;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLException;

import database.DbAdapter;
import entità.Chef;

public class TEST_DB extends Activity {

    private DbAdapter adapter = new DbAdapter(this);
    private Cursor cursor;
    TextView demoTextView;
    Button show_button;
    Button delete_button;
    Button insert_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__db);
        demoTextView = (TextView)findViewById(R.id.demoTextView);
        show_button = (Button)findViewById(R.id.button1);
        delete_button = (Button)findViewById(R.id.button2);
        insert_button = findViewById(R.id.button3);

        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoTextView.setText("");
                try {
                    adapter.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                cursor = adapter.fetchAllChefs();
                while ( cursor.moveToNext() ) {
                    String chefID = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_EMAIL) );
                    String nome = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_NOME) );
                    String cognome = cursor.getString( cursor.getColumnIndex(DbAdapter.KEY_COGNOME) );
                    String text ="Email: " + chefID + ", Nome: " + nome + ", Cognome: " + cognome + "\n";
                    demoTextView.setText(demoTextView.getText().toString() + text);
                }
                adapter.close();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().deleteDatabase("mydatabase.db");
            }
        });

        insert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    adapter.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adapter.insertFast(3);
                adapter.close();
            }
        });
    }
}
