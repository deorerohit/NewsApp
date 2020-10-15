package dev.deorerohit.newsapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//  * Your API key is: 3ec9808ce7d144cbb979b03e32aec1e9


public class MainActivity extends AppCompatActivity {

    TextView sample_textView;
    private Retrofit retrofit;
    //  private NewsAPIInterface newsAPIInterface;
    private static final String API_KEY = "3ec9808ce7d144cbb979b03e32aec1e9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sample_textView = findViewById(R.id.sample_textView);


        final NewsAPIInterface newsAPIInterface = NewsAPIClient.getClient().create(NewsAPIInterface.class);

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
        });
    }




}

   