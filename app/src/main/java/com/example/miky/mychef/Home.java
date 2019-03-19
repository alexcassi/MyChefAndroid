package com.example.miky.mychef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import preferenze.Sessione;

public class Home extends FragmentActivity {

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
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
    }

    public void goToSignup () {
        Intent intent = new Intent(Home.this, SignUp.class);
        startActivity(intent);
    }

    public void goToLogout () {
        Sessione.logout(getApplicationContext());
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        Toast.makeText(Home.this,
                "Logout eseguito",
                Toast.LENGTH_LONG).show();
    }

    public void goToCercaChef(){

    }

    public void goToRicette(){
        Intent intent = new Intent(Home.this, Ricette.class);
        startActivity(intent);
    }

    public void goToHome(View view){
        Intent intent = new Intent(view.getContext(),Home.class);
        view.getContext().startActivity(intent);
    }

}

