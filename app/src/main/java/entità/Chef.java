package entità;

import com.google.gson.annotations.SerializedName;

public class Chef {

    @SerializedName("email")
    String email;
    @SerializedName("nome")
    String nome;
    @SerializedName("cognome")
    String cognome;

    public Chef(){

    }

    public Chef(String email, String nome, String cognome){
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
