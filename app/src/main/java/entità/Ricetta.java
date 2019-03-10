package entità;

import com.google.gson.annotations.SerializedName;

public class Ricetta {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nome_ricetta")
    private String nome_ricetta;
    @SerializedName("ingredienti")
    private String ingredienti;
    @SerializedName("tempo_preparazione")
    private String tempo_preparazione;
    @SerializedName("prezzo")
    private Double prezzo;
    @SerializedName("chef")
    private Chef chef;
    @SerializedName("immagine_ricetta")
    private String immagine_ricetta;

    public Ricetta() {
    }

    public String getNome_ricetta() {
        return nome_ricetta;
    }

    public void setNome_ricetta(String nome_ricetta) {
        this.nome_ricetta = nome_ricetta;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getTempo_preparazione() {
        return tempo_preparazione;
    }

    public void setTempo_preparazione(String tempo_preparazione) {
        this.tempo_preparazione = tempo_preparazione;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getId() {
        return id;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public String getImmagine_ricetta() {
        return immagine_ricetta;
    }

    public void setImmagine_ricetta(String immagine_ricetta) {
        this.immagine_ricetta = immagine_ricetta;
    }
}
