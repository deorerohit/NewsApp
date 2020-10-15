package dev.deorerohit.newsapp.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import dev.deorerohit.newsapp.Models.Article;
import dev.deorerohit.newsapp.Models.ResponseModel;
import dev.deorerohit.newsapp.R;
import dev.deorerohit.newsapp.Viewmodel.NewsViewModel;

//  * Your API key is: 3ec9808ce7d144cbb979b03e32aec1e9


public class MainActivity extends AppCompatActivity {

    TextView sample_textView;
    NewsViewModel newsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sample_textView = findViewById(R.id.sample_textView);


        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        newsViewModel.getAllNewsFromViewModel().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                List<Article> articleList = responseModel.getArticles();

                if (articleList != null) {
                    for (Article comment : articleList) {
                        String printIt = "";
                        printIt += "Author : " + comment.getAuthor() + "\n";
                        printIt += "Content      : " + comment.getContent() + "\n";
                        printIt += "Desc    : " + comment.getDescription() + "\n";
                        printIt += "Published At   : " + comment.getPublishedAt() + "\n";
                        printIt += "Source    : " + comment.getSource() + "\n\n";
                        printIt += "Title    : " + comment.getTitle() + "\n\n";
                        printIt += "URL    : " + comment.getUrl() + "\n\n";
                        printIt += "URL to image    : " + comment.getUrlToImage() + "\n\n";
                        sample_textView.append(printIt);
                    }
                } else {
                    sample_textView.setText("Empty data camed");
                }
            }
        });


    }


}




       /* final NewsAPIInterface newsAPIInterface = NewsAPIClient.getClient().create(NewsAPIInterface.class);
        Call<ResponseModel> call = newsAPIInterface.getLatestNews("in", "general", API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();

                    if (articleList != null) {
                        for (Article comment : articleList) {
                            String printIt = "";
                            printIt += "Author : " + comment.getAuthor() + "\n";
                            printIt += "Content      : " + comment.getContent() + "\n";
                            printIt += "Desc    : " + comment.getDescription() + "\n";
                            printIt += "Published At   : " + comment.getPublishedAt() + "\n";
                            printIt += "Source    : " + comment.getSource() + "\n\n";
                            printIt += "Title    : " + comment.getTitle() + "\n\n";
                            printIt += "URL    : " + comment.getUrl() + "\n\n";
                            printIt += "URL to image    : " + comment.getUrlToImage() + "\n\n";
                            sample_textView.append(printIt);
                        }
                    }

                } else {
                    sample_textView.setText("Empty data camed");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                sample_textView.setText(t.getMessage());
            }
        });*/
   