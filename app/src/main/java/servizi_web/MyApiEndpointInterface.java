package servizi_web;

import java.util.List;
import java.util.TreeMap;

import entità.Chef;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {
    @GET("/web-mychef/ListaChefServletAndroid")
    Call<List<Chef>> getChefs();

    @GET("/web-mychef/LoginControllerAndroid")
    Call<TreeMap<String,String>> login(@Query("email") String email, @Query("password") String password);

    @FormUrlEncoded
    @POST ("/web-mychef/SignUpChefControllerAndroid")
    Call<String> signupChef(@Field("email") String email, @Field("password") String password,
                            @Field("nome") String nome, @Field("cognome") String cognome,
                            @Field("luogo_lavoro") String luogo_lavoro);

}
