package endpoint;

import java.util.List;

import entità.Chef;
import entità.Utente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {
    @GET("/web-mychef/ListaChefServletAndroid")
    Call<List<Chef>> getChefs();

    @GET("/web-mychef/LoginControllerAndroid")
    Call<Utente> login(@Query("email") String email, @Query("password") String password);
}
