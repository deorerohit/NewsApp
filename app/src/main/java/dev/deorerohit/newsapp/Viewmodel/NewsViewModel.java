package dev.deorerohit.newsapp.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.deorerohit.newsapp.Models.ResponseModel;
import dev.deorerohit.newsapp.Repository.Repository;

public class NewsViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ResponseModel> responseModelMutableLiveData;




    public NewsViewModel() {

       // repository = new Repository(application);
        repository = Repository.getInstance();
        responseModelMutableLiveData = repository.getAllNewsInRepository("in", "general");
    }

    public LiveData<ResponseModel> getAllNewsFromViewModel() {
        return responseModelMutableLiveData;
    }
}
