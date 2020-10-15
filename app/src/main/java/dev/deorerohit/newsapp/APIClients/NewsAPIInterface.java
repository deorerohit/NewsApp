package dev.deorerohit.newsapp.APIClients;

import dev.deorerohit.newsapp.Models.ResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {


    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("country") String country, @Query("category") String source, @Query("apiKey") String apiKey);


}
