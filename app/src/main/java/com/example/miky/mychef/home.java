package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import preferenze.Preferenze;
import preferenze.Sessione;

public class home extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Sessione.isCliente(getApplicationContext())){
            getSupportFragmentManager().beginTransaction().add(R.id.frame_home,
                    new FRAG_home_cliente()).commit();
        } else {
            if (Sessione.isChef(getApplicationContext())){
                getSupportFragmentManager().beginTransaction().add(R.id.frame_home,
                        new FRAG_home_chef()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.frame_home,
                        new FRAG_home_not_logged()).commit();
            }
        }
    }

    public void goToLogin () {
        Intent intent = new Intent(home.this, Login.class);
        startActivity(intent);
    }

    public void goToSignup () {
        Intent intent = new Intent(home.this, SignUp.class);
        startActivity(intent);
    }

    public void goToLogout () {
        Sessione.logout(getApplicationContext());
    }

    public void goToCercaChef(){

    }

    public void goToRicette(){
        Intent intent = new Intent(home.this, ricette.class);
        startActivity(intent);
    }

}

