package dev.deorerohit.newsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {


    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("country") String country, @Query("category") String source, @Query("apiKey") String apiKey);


}
