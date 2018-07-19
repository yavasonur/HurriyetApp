package com.app.onur.hurriyetapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.onur.hurriyetapp.Adapters.FavoriteRvAdapter;
import com.app.onur.hurriyetapp.Database.Db_Layer;
import com.app.onur.hurriyetapp.Model.MyArticle;
import com.app.onur.hurriyetapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteFragment extends Fragment {

    @BindView(R.id.recyclerView_Favorite) RecyclerView recyclerView;
    @BindView(R.id.txtResultFavorite) TextView txtResulFavorite;

    ArrayList<MyArticle> articles = new ArrayList<>();
    Db_Layer db;

    public static FavoriteRvAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);

        db = new Db_Layer(getContext());

        ButterKnife.bind(this,view);

        db.openDb();

        articles = db.FavoriteArticleList();

        if(articles.size() > 0){

            txtResulFavorite.setVisibility(View.INVISIBLE);

            adapter = new FavoriteRvAdapter(getContext(),articles);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            recyclerView.setAdapter(adapter);
        }else{

            txtResulFavorite.setVisibility(View.VISIBLE);
        }

        return view;

    }
}
