package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.TreeMap;

import database.DbAdapterChef;
import database.DbAdapterCliente;
import preferenze.Sessione;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import servizi_web.ServerUtility;

public class Login extends Activity {

    Button login;
    EditText user;
    EditText password;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.nome_utente);
        password = findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<TreeMap<String,String>> call_utente = ServerUtility.getApiService()
                        .login(user.getText().toString(), password.getText().toString());
                call_utente.enqueue(new Callback<TreeMap<String,String>>() {
                    @Override
                    public void onResponse(Call<TreeMap<String,String>> call,
                                           Response<TreeMap<String,String>> response) {
                        boolean login_avvenuto = false;
                        TreeMap<String,String> risposta = response.body();

                        //caso chef
                        if(risposta.containsKey("luogo_lavoro")){
                            Sessione.loginChef(getApplicationContext(),
                                    risposta.get("email"),risposta.get("password"),risposta.get("nome"));
                            DbAdapterChef adapter_chef = new DbAdapterChef(getApplicationContext());
                            try {
                                adapter_chef.open();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if(!adapter_chef.updateChef(risposta)){
                                adapter_chef.createChef(risposta);
                            }
                            login_avvenuto = true;

                        } else {
                            //caso cliente
                            if (risposta.containsKey("indirizzo")) {
                                Sessione.loginCliente(getApplicationContext(),
                                        risposta.get("email"),risposta.get("password"),risposta.get("nome"));
                                DbAdapterCliente adapter_cliente = new DbAdapterCliente(getApplicationContext());
                                try {
                                    adapter_cliente.open();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                if(adapter_cliente.containsCliente(risposta.get("email"))){
                                    adapter_cliente.updateCliente(risposta);
                                } else {
                                    adapter_cliente.updateCliente(risposta);
                                }
                                login_avvenuto = true;

                            } else {
                                //caso login fallito
                                toast =  Toast.makeText(getApplicationContext(),
                                        risposta.get(("messaggio")), Toast.LENGTH_LONG);
                                toast.show();
                                login_avvenuto = false;
                            }
                        }
                        if (login_avvenuto){
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<TreeMap<String,String>> call, Throwable t) {
                        toast =  Toast.makeText(getApplicationContext(),
                                "Errore interno. Riprovare. Se persiste contattarci", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        });

    }
}
