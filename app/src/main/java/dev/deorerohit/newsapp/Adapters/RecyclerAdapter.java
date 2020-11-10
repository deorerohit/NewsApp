package dev.deorerohit.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import dev.deorerohit.newsapp.Activity.ViewFullNewsActivity;
import dev.deorerohit.newsapp.Models.Article;
import dev.deorerohit.newsapp.Models.SourceModel;
import dev.deorerohit.newsapp.R;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataHolderClass> {


    public static final String IMAGE_KEY = "imageToPass";
    public static final String TITLE_KEY = "titleToPass";
    public static final String AUTHOR_KEY = "authorToPass";
    public static final String TIME_KEY = "timeToPass";
    public static final String DESC_KEY = "descToPass";
    public static final String CONTENT_KEY = "contentToPass";
    public static final String URL_KEY = "urlToPass";
    public static final String SOURCE_KEY = "sourceToPass";


    private List<Article> articleList = new ArrayList<>();
    private Context mainActivity;

    public RecyclerAdapter(Context mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerAdapter.DataHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recycler_layout, parent, false);
        return new DataHolderClass(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.DataHolderClass holder, int position) {
        Article article = articleList.get(position);

        holder.newsTitle_textview.setText(article.getTitle());
        holder.newsFrom_textview.setText(article.getAuthor());

        if (article.getUrlToImage() != null) {
            Glide.with(mainActivity)
                    .load(article.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .thumbnail(0.1f)
                    .centerCrop()
                    .into(holder.thumbnail_imageview);

        } else {
            Glide.with(mainActivity)
                    .load(R.drawable.news_thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .thumbnail(0.1f)
                    .centerCrop()
                    .into(holder.thumbnail_imageview);
        }


    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setNewsList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }


    public class DataHolderClass extends RecyclerView.ViewHolder {

        private TextView newsTitle_textview;
        private TextView newsFrom_textview;
        private ImageView thumbnail_imageview;


        public DataHolderClass(@NonNull View itemView) {
            super(itemView);

            newsFrom_textview = itemView.findViewById(R.id.newsFrom_textview);
            newsTitle_textview = itemView.findViewById(R.id.newsTitle_textview);
            thumbnail_imageview = itemView.findViewById(R.id.thumbnail_imageview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Article article = articleList.get(getAdapterPosition());
                    SourceModel sourceModel = article.getSource();
                    String source = sourceModel.getName();

                    Intent intent = new Intent(mainActivity, ViewFullNewsActivity.class);
                    intent.putExtra(IMAGE_KEY, article.getUrlToImage());
                    intent.putExtra(TITLE_KEY, article.getTitle());
                    intent.putExtra(AUTHOR_KEY, article.getAuthor());
                    intent.putExtra(TIME_KEY, article.getPublishedAt());
                    intent.putExtra(DESC_KEY, article.getDescription());
                    intent.putExtra(CONTENT_KEY, article.getContent());
                    intent.putExtra(URL_KEY, article.getUrl());
                    intent.putExtra(SOURCE_KEY, source);
                    mainActivity.startActivity(intent);

                }
            });
        }


    }
}
