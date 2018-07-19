package com.app.onur.hurriyetapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.onur.hurriyetapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {

    @BindView(R.id.webView) WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("link");

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
