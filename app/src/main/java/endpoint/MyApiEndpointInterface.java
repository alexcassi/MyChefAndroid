package endpoint;

import java.util.List;

import entità.Chef;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiEndpointInterface {
    @GET("/web-mychef/ListaChefServlet")
    Call<List<Chef>> getChefs();
}
