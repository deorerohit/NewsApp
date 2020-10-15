package dev.deorerohit.newsapp.Repository;

import androidx.lifecycle.MutableLiveData;

import dev.deorerohit.newsapp.APIClients.NewsAPIClient;
import dev.deorerohit.newsapp.APIClients.NewsAPIInterface;
import dev.deorerohit.newsapp.Models.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String API_KEY = "3ec9808ce7d144cbb979b03e32aec1e9";
    private static Repository repository;
    private final NewsAPIInterface newsAPIInterface;


    public Repository() {
        newsAPIInterface = NewsAPIClient.getClient().create(NewsAPIInterface.class);
    }


    public static Repository getInstance() {

        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }


    public MutableLiveData<ResponseModel> getAllNewsInRepository(String country, String source) {

        final MutableLiveData<ResponseModel> newsDataToReturn = new MutableLiveData<>();


        Call<ResponseModel> call = newsAPIInterface.getLatestNews(country, source, API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.body().getStatus().equals("ok")) {
                    newsDataToReturn.setValue(response.body());
                } else {
                    System.out.println("Error%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                   newsDataToReturn.setValue(null);
            }
        });
        return newsDataToReturn;
    }


}
