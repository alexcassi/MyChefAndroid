package entità;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chef extends Utente {

    @SerializedName("luogo_lavoro")
    private String luogo_lavoro;
    @SerializedName("ricette")
    private List<Ricetta> ricette;
    @SerializedName("immagine_profilo")
    private String immagine_profilo;

    public Chef() {
    }

    public List<Ricetta> getRicette() {
        return ricette;
    }

    public void setRicette(List<Ricetta> ricette) {
        this.ricette = ricette;
    }

    public String getLuogo_lavoro() {
        return luogo_lavoro;
    }

    public void setLuogo_lavoro(String luogo_lavoro) {
        this.luogo_lavoro = luogo_lavoro;
    }

    public String getImmagine_profilo() {
        return immagine_profilo;
    }

    public void setImmagine_profilo(String immagine_profilo) {
        this.immagine_profilo = immagine_profilo;
    }

}