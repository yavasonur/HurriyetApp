package com.app.onur.hurriyetapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.app.onur.hurriyetapp.Activities.ArticleDetailActivity;
import com.app.onur.hurriyetapp.Database.Db_Layer;
import com.app.onur.hurriyetapp.Fragments.FavoriteFragment;
import com.app.onur.hurriyetapp.Model.Article;
import com.app.onur.hurriyetapp.Model.MyArticle;
import com.app.onur.hurriyetapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteRvAdapter extends RecyclerView.Adapter<FavoriteRvAdapter.MyFavoriteViewHolder> {

    private Context context;
    private ArrayList<MyArticle> mData;

    Db_Layer dbLayer;

    public FavoriteRvAdapter(Context context, ArrayList list){
        this.context = context;
        this.mData = list;
        dbLayer = new Db_Layer(context);
        dbLayer.openDb();
    }


    @NonNull
    @Override
    public MyFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.favorite_item_row,parent,false);
        final MyFavoriteViewHolder viewHolder = new MyFavoriteViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoriteViewHolder holder, final int position) {

        holder.txtHeader_favorite.setText(mData.get(position).getTitle());

        Glide.with(context).load(mData.get(position).getFileUrl()).into(holder.imageView_favorite1);


        holder.imageFavorite_favorite.setImageResource(R.drawable.favorite_image_select);

        holder.imageFavorite_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Article arc = new Article(mData.get(position).getId(),null,null,null);
                dbLayer.FavoriteArticleRemove(arc);
                mData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mData.size());
            }
        });

        holder.imageShare_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareLink(mData.get(position));
            }
        });

        holder.cardViewContainer_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                articleDetailOpen(mData.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void shareLink(MyArticle article) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String url = article.getUrl();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Article Link");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        context.startActivity(Intent.createChooser(sharingIntent, "Article Share"));
    }
    private void articleDetailOpen(String url) {
        Intent i = new Intent(context, ArticleDetailActivity.class);
        i.putExtra("link",url);
        context.startActivity(i);
    }

    public static class MyFavoriteViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardViewContainer_favorite)
        CardView cardViewContainer_favorite;
        @BindView(R.id.imageView_favorite)
        ImageView imageView_favorite1;
        @BindView(R.id.txtHeader_favorite)
        TextView txtHeader_favorite;
        @BindView(R.id.imageShare_favorite)
        ImageButton imageShare_favorite;
        @BindView(R.id.imageFavorite_favorite) ImageButton imageFavorite_favorite;

        public MyFavoriteViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
