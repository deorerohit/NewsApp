package dev.deorerohit.newsapp.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import dev.deorerohit.newsapp.Adapters.RecyclerAdapter;
import dev.deorerohit.newsapp.R;

public class ViewFullNewsActivity extends AppCompatActivity {

    ImageView articleImage_imageView;
    TextView articleTitle_textView;
    TextView articleAuthor_textView;
    TextView articleTime_textView;
    TextView articleDesc_textView;
    TextView articleSource_textView;
    TextView articleContent_textView;
    TextView articleURL_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_news);

        articleAuthor_textView = findViewById(R.id.articleAuthor_textView);
        articleDesc_textView = findViewById(R.id.articleDesc_textView);
        articleImage_imageView = findViewById(R.id.articleImage_imageView);
        articleSource_textView = findViewById(R.id.articleSource_textView);
        articleTime_textView = findViewById(R.id.articleTime_textView);
        articleTitle_textView = findViewById(R.id.articleTitle_textView);
        articleContent_textView = findViewById(R.id.articleContent_textView);
        articleURL_textView = findViewById(R.id.articleURL_textView);

        Intent intent = getIntent();

        articleAuthor_textView.setText(intent.getStringExtra(RecyclerAdapter.AUTHOR_KEY));
        articleDesc_textView.setText(intent.getStringExtra(RecyclerAdapter.DESC_KEY));
        articleTime_textView.setText(intent.getStringExtra(RecyclerAdapter.TIME_KEY));
        articleTitle_textView.setText(intent.getStringExtra(RecyclerAdapter.TITLE_KEY));
        articleContent_textView.setText(intent.getStringExtra(RecyclerAdapter.CONTENT_KEY));
        articleURL_textView.setText("Continue reading...");


        String dynamicUrl = intent.getStringExtra(RecyclerAdapter.URL_KEY);

        String linkedText =
                String.format("<a href=\"%s\">Continue reading...</a> ", dynamicUrl);

    /*    String linkedText = "<b>text3:</b>  Text with a " +
                String.format("<a href=\"%s\">link</a> ", dynamicUrl) +
                "Continue reading...";*/

        articleURL_textView.setText(Html.fromHtml(linkedText));
        articleURL_textView.setMovementMethod(LinkMovementMethod.getInstance());


        // articleSource_textView.setText(intent.getStringExtra(RecyclerAdapter.SOURCE_KEY));
        System.out.println(articleImage_imageView.getMeasuredWidth()+"************************************************");
        System.out.println(articleImage_imageView.getMeasuredHeight()+"***************************************////////*********");
        Picasso.get()
                .load(intent.getStringExtra(RecyclerAdapter.IMAGE_KEY))
                .resize(0, 200)
                .centerCrop().into(articleImage_imageView);


    }
}