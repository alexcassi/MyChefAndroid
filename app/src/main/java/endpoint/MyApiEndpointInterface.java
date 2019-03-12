package endpoint;

import java.util.List;

import entità.Chef;
import entità.Cliente;
import entità.Utente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {
    @GET("/web-mychef/ListaChefServletAndroid")
    Call<List<Chef>> getChefs();

    @GET("/web-mychef/LoginChefControllerAndroid")
    Call<Chef> loginChef(@Query("email") String email, @Query("password") String password);

    @GET("/web-mychef/LoginClienteControllerAndroid")
    Call<Cliente> loginCliente(@Query("email") String email, @Query("password") String password);

}
