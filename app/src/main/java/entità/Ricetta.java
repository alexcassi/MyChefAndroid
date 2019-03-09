package entità;

public class Ricetta {

    Integer id;
    String nome;
    String ingredienti;

    public Ricetta(){

    }

    public Ricetta(Integer id, String nome, String ingredienti){
        this.id = id;
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }
}
