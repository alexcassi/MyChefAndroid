package endpoint;

import entità.Chef;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiEndpointInterface {
    @GET("/ListaChefServlet")
    Call<Chef> getChefs();
}
