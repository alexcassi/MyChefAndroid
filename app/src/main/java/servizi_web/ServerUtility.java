package servizi_web;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerUtility {

    private static final String BASE_URL = "http://10.0.2.2:8080/";

    private static Retrofit retrofit;

    private static MyApiEndpointInterface apiService;

    public static MyApiEndpointInterface getApiService(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        if (apiService == null) {
            apiService = retrofit.create(MyApiEndpointInterface.class);
        }
        return apiService;
    }

}
