package com.example.miky.mychef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Activity {

    Button signupBT;
    EditText nome, cognome, email, pass1, pass2, luogo_lavoro, indirizzo, provincia, comune;
    EmailValidator emailValidator;
    boolean chef_or_cliente; //true -> chef, false -> cliente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailValidator = new EmailValidator();
        chef_or_cliente = true;

        nome = findViewById(R.id.nome);
        nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    nome.setError("campo obbligatorio");
                }
            }
        });
        cognome = findViewById(R.id.cognome);
        cognome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    cognome.setError("campo obbligatorio");
                }
            }
        });
        email = findViewById(R.id.email);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!emailValidator.validate(editable.toString())){
                    email.setError("inserire email valida");
                }
                if (editable.toString().length() == 0){
                    email.setError("campo obbligatorio");
                }
            }
        });
        pass1 = findViewById(R.id.pass);
        pass1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                pass2.setError(null);
                if (editable.toString().length() == 0){
                    pass1.setError("campo obbligatorio");
                }
                if (editable.toString().length() < 6){
                    pass1.setError("inserire almeno 6 caratteri");
                }
                if (!editable.toString().equals(pass2.getText().toString())){
                    pass1.setError("le password non coincidono");
                }
            }
        });
        pass2 = findViewById(R.id.pass2);
        pass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                pass1.setError(null);
                if (editable.toString().length() == 0){
                    pass2.setError("campo obbligatorio");
                }
                if (!editable.toString().equals(pass1.getText().toString())){
                    pass2.setError("le password non coincidono");
                }
            }
        });
        luogo_lavoro = findViewById(R.id.luogo_lavoro);
        luogo_lavoro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    luogo_lavoro.setError("campo obbligatorio");
                }
            }
        });
        indirizzo = findViewById(R.id.indirizzo);
        indirizzo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    indirizzo.setError("campo obbligatorio");
                }
            }
        });
        comune= findViewById(R.id.comune);
        comune.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    comune.setError("campo obbligatorio");
                }
            }
        });
        provincia = findViewById(R.id.provincia);
        provincia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 0){
                    provincia.setError("campo obbligatorio");
                }
            }
        });

        signupBT = findViewById(R.id.signup);
        signupBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean errore = false;
                //campi vuoti
                if (nome.getText().toString().length() == 0 || cognome.getText().toString().length() == 0 ||
                        email.getText().toString().length() == 0 || pass1.getText().toString().length() == 0 ||
                        pass2.getText().toString().length() == 0 ){
                    errore = true;
                }
                //campi in errore
                if (nome.getError()!=null || cognome.getError()!=null || email.getError()!=null ||
                        pass1.getError()!=null || pass2.getError()!=null){
                    errore = true;
                }
                //campi vuoti caso chef
                if(chef_or_cliente && luogo_lavoro.getText().toString().length() == 0){
                    errore = true;
                }
                //campi in errore caso chef
                if(chef_or_cliente && luogo_lavoro.getError()!=null){
                    errore = true;
                }
                //campi vuoti caso cliente
                if((!chef_or_cliente) && (indirizzo.getText().toString().length() == 0 ||
                provincia.getText().toString().length() == 0 || comune.getText().toString().length() == 0)){
                    errore = true;
                }
                //campi in errore caso cliente
                if ((!chef_or_cliente) && (indirizzo.getError()!=null || provincia.getError()!=null ||
                        comune.getError()!=null)){
                    errore = true;
                }
                //controlla se ci sono errori
                if(errore){
                    Toast.makeText(SignUp.this,"correggere i campi",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUp.this,"campi corretti",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void mostraCampiChef(View view){
        RelativeLayout chef_buttons = findViewById(R.id.gruppoChef);
        RelativeLayout cliente_buttons = findViewById(R.id.gruppoCliente);
        cliente_buttons.setVisibility(View.GONE);
        chef_buttons.setVisibility(View.VISIBLE);
        chef_or_cliente =true;
    }
    public void mostraCampiCliente(View view){
        RelativeLayout chef_buttons = findViewById(R.id.gruppoChef);
        RelativeLayout cliente_buttons = findViewById(R.id.gruppoCliente);
        cliente_buttons.setVisibility(View.VISIBLE);
        chef_buttons.setVisibility(View.GONE);
        chef_or_cliente = false;
    }

    public class EmailValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        public boolean validate(final String hex) {
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }
    }
}
