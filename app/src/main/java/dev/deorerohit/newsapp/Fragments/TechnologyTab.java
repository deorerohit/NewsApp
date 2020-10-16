package dev.deorerohit.newsapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dev.deorerohit.newsapp.Adapters.RecyclerAdapter;
import dev.deorerohit.newsapp.Models.Article;
import dev.deorerohit.newsapp.Models.ResponseModel;
import dev.deorerohit.newsapp.R;
import dev.deorerohit.newsapp.Viewmodel.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechnologyTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechnologyTab extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TechnologyTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechnologyTab.
     */
    // TODO: Rename and change types and number of parameters
    public static TechnologyTab newInstance(String param1, String param2) {
        TechnologyTab fragment = new TechnologyTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_technology_tab, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_layout_technologyTab);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(rootView.getContext());
        recyclerView.setAdapter(recyclerAdapter);

        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.initDataToGetNews("in", "technology");

        newsViewModel.getAllNewsFromViewModel().observe(this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                List<Article> articleList = responseModel.getArticles();
                recyclerAdapter.setNewsList(articleList);

            }
        });

        return rootView;
    }
}