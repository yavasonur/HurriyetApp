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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.app.onur.hurriyetapp.Activities.CornerPostActivity;
import com.app.onur.hurriyetapp.Model.Author;
import com.app.onur.hurriyetapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AuthorRvAdapter extends RecyclerView.Adapter<AuthorRvAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Author> authors;

    public AuthorRvAdapter(Context context, ArrayList list){
        this.context = context;
        this.authors = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.author_item_row,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.authorTexrView.setText(authors.get(position).getFullname());

        if(authors.get(position).getFiles().size() > 0){

            Glide.with(context).load(authors.get(position).getFiles().get(0).getFileUrl()).into(holder.authorImageView);

        }else{

            Glide.with(context).load(R.mipmap.hurriyet_icon).into(holder.authorImageView);
        }

        holder.authorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, CornerPostActivity.class);
                i.putExtra("writerId", authors.get(position).getId().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.authorImageView) CircleImageView authorImageView;
        @BindView(R.id.authorTextView) TextView authorTexrView;
        @BindView(R.id.authorCardView) CardView authorCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
