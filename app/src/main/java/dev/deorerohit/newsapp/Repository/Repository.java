package dev.deorerohit.newsapp.Repository;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import dev.deorerohit.newsapp.APIClients.NewsAPIClient;
import dev.deorerohit.newsapp.APIClients.NewsAPIInterface;
import dev.deorerohit.newsapp.Fragments.EntertainmentTab;
import dev.deorerohit.newsapp.Fragments.GeneralTab;
import dev.deorerohit.newsapp.Fragments.HealthTab;
import dev.deorerohit.newsapp.Fragments.ScienceTab;
import dev.deorerohit.newsapp.Fragments.SportsTab;
import dev.deorerohit.newsapp.Fragments.TechnologyTab;
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


    public MutableLiveData<ResponseModel> getAllNewsInRepository(String country, String category) {


        final MutableLiveData<ResponseModel> newsDataToReturn = new MutableLiveData<>();
        Call<ResponseModel> call = newsAPIInterface.getLatestNews(country, category, API_KEY);
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
