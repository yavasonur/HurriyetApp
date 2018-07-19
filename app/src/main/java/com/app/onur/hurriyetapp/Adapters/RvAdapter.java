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
import com.app.onur.hurriyetapp.Model.Article;
import com.app.onur.hurriyetapp.Model.ArticlePhotos;
import com.app.onur.hurriyetapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvAdapter extends RecyclerView.Adapter <RvAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Article> mData;

    Db_Layer dbLayer;


    public RvAdapter(Context context, ArrayList list){
        this.context = context;
        this.mData = list;
        dbLayer = new Db_Layer(context);
        dbLayer.openDb();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_row,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        try {

            holder.txtHeader.setText(mData.get(position).getTitle());

            String url = mData.get(position).getFiles().get(0).getFileUrl();

            Glide.with(context).load(url).into(holder.imageView);

            if(dbLayer.FavoriteArticleFind(mData.get(position).getId()))
                holder.imageFavorite.setImageResource(R.drawable.favorite_image_select);
            else
                holder.imageFavorite.setImageResource(R.drawable.favorite_image);

            holder.imageShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareLink(mData.get(position));
                }
            });
            holder.cardViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    articleDetailOpen(mData.get(position).getUrl());
                }
            });

            holder.imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int id = mData.get(0).getId();

                    if(dbLayer.FavoriteArticleFind(mData.get(position).getId())){
                        dbLayer.FavoriteArticleRemove(mData.get(position));
                        holder.imageFavorite.setImageResource(R.drawable.favorite_image);
                    }
                    else{
                        dbLayer.FavoriteArticleAdd(mData.get(position));
                        holder.imageFavorite.setImageResource(R.drawable.favorite_image_select);
                    }

                }
            });

        }catch (Exception e){

        }


    }

    private void articleDetailOpen(String url) {
        Intent i = new Intent(context, ArticleDetailActivity.class);
        i.putExtra("link",url);
        context.startActivity(i);
    }

    private void shareLink(Article article) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String url = article.getUrl();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Article Link");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        context.startActivity(Intent.createChooser(sharingIntent, "Article Share"));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardViewContainer) CardView cardViewContainer;
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.txtHeader) TextView txtHeader;
        @BindView(R.id.imageShare) ImageButton imageShare;
        @BindView(R.id.imageFavorite) ImageButton imageFavorite;

        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
