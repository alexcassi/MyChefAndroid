package entità;

import com.google.gson.annotations.SerializedName;

public class Cliente extends Utente {

    @SerializedName("provincia")
    private String provincia;
    @SerializedName("comune")
    private String comune;
    @SerializedName("indirizzo")
    private String indirizzo;

    public Cliente() {
    }

    // private Double latitudine;
    // private Double longitudine;

    public String getProvincia() {
        return provincia;
    }

    public String getComune() {
        return comune;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

}
