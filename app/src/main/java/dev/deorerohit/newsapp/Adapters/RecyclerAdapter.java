package dev.deorerohit.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dev.deorerohit.newsapp.Activity.MainActivity;
import dev.deorerohit.newsapp.Models.Article;
import dev.deorerohit.newsapp.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataHolderClass> {

    private List<Article> articleList = new ArrayList<>();
    private Context mainActivity;

    public RecyclerAdapter(Context mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerAdapter.DataHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recycler_layout,parent, false);
        return new DataHolderClass(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.DataHolderClass holder, int position) {
        Article article = articleList.get(position);

        holder.newsTitle_textview.setText(article.getTitle());
        holder.newsFrom_textview.setText(article.getAuthor());

        if(article.getUrlToImage()!= null) {
            Glide.with(mainActivity)
                    .load(article.getUrlToImage())
                    .thumbnail(0.1f)
                    .override(250,250)
                    .centerCrop()
                    .into(holder.thumbnail_imageview);

        }
        else {
            Glide.with(mainActivity)
                    .load(R.drawable.news_thumbnail)
                    .thumbnail(0.1f)
                    .override(250,250)
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
        }


    }
}
