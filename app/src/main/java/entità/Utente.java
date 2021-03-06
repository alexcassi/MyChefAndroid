package entità;

import com.google.gson.annotations.SerializedName;

public class Utente {

    @SerializedName("email")
    private String email;
    @SerializedName("nome")
    private String nome;
    @SerializedName("cognome")
    private String cognome;

    public Utente() {
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
