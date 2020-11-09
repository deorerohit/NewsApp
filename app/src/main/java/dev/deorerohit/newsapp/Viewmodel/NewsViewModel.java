package dev.deorerohit.newsapp.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tuyenmonkey.mkloader.MKLoader;

import dev.deorerohit.newsapp.Models.ResponseModel;
import dev.deorerohit.newsapp.Repository.Repository;

public class NewsViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ResponseModel> responseModelMutableLiveData;




    public NewsViewModel() {
        // repository = new Repository(application);
        repository = Repository.getInstance();
       // responseModelMutableLiveData = repository.getAllNewsInRepository("in");
    }

    public void initDataToGetNews(String country, String category) {
        responseModelMutableLiveData = repository.getAllNewsInRepository(country,category);
    }

    public LiveData<ResponseModel> getAllNewsFromViewModel() {
        return responseModelMutableLiveData;
    }


}
