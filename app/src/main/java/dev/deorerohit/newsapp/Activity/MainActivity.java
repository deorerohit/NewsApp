package dev.deorerohit.newsapp.Activity;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import dev.deorerohit.newsapp.Adapters.CategoryPageAdapter;
import dev.deorerohit.newsapp.Adapters.RecyclerAdapter;
import dev.deorerohit.newsapp.Fragments.EntertainmentTab;
import dev.deorerohit.newsapp.Fragments.GeneralTab;
import dev.deorerohit.newsapp.Fragments.HealthTab;
import dev.deorerohit.newsapp.Fragments.ScienceTab;
import dev.deorerohit.newsapp.Fragments.SportsTab;
import dev.deorerohit.newsapp.Fragments.TechnologyTab;
import dev.deorerohit.newsapp.Models.Article;
import dev.deorerohit.newsapp.Models.ResponseModel;
import dev.deorerohit.newsapp.R;
import dev.deorerohit.newsapp.Viewmodel.NewsViewModel;

//  * Your API key is: 3ec9808ce7d144cbb979b03e32aec1e9


public class MainActivity extends AppCompatActivity {


    NewsViewModel newsViewModel;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    private TabLayout tabLayout_mainActivity;
    private ViewPager2 viewPager2_mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout_mainActivity = findViewById(R.id.tablayout_mainActivity);
        viewPager2_mainActivity = findViewById(R.id.viewPager_mainActivity);

        initializeViewPager();

        /* recyclerView = findViewById(R.id.recyclerView_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);


        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.initDataToGetNews("in", "entertainment");

        newsViewModel.getAllNewsFromViewModel().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                List<Article> articleList = responseModel.getArticles();
                recyclerAdapter.setNewsList(articleList);

            }
        });*/


    }

    private void initializeViewPager() {

        CategoryPageAdapter categoryPageAdapter = new CategoryPageAdapter(this);
        categoryPageAdapter.addFragments(new GeneralTab(), "General");
        categoryPageAdapter.addFragments(new HealthTab(), "Health");
        categoryPageAdapter.addFragments(new ScienceTab(), "Science");
        categoryPageAdapter.addFragments(new SportsTab(), "Sports");
        categoryPageAdapter.addFragments(new TechnologyTab(), "Technology");
        categoryPageAdapter.addFragments(new EntertainmentTab(), "Entertainment");

        viewPager2_mainActivity.setAdapter(categoryPageAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout_mainActivity, viewPager2_mainActivity, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {

                    case 0 : tab.setText("General");
                             break;
                    case 1 : tab.setText("Health");
                        break;
                    case 2 : tab.setText("Science");
                        break;
                    case 3 : tab.setText("Sports");
                        break;
                    case 4 : tab.setText("Technology");
                        break;
                    case 5 : tab.setText("Entertainment");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();

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
   