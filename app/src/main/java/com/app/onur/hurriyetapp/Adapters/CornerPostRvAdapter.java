package com.app.onur.hurriyetapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.onur.hurriyetapp.Model.CornerPost;
import com.app.onur.hurriyetapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CornerPostRvAdapter extends RecyclerView.Adapter<CornerPostRvAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<CornerPost> cornerPosts;

    public CornerPostRvAdapter(Context context, ArrayList list){
        this.context = context;
        this.cornerPosts = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(R.layout.corner_post_item_row,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cornerPostTxtHeader.setText(cornerPosts.get(position).getTitle());
        holder.cornerPostTxtDescription.setText(cornerPosts.get(position).getDescription());
        holder.cornerPostTxtDate.setText(cornerPosts.get(position).getCreatedDate().toString());

    }

    @Override
    public int getItemCount() {
        return cornerPosts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cornerPostImageView) CircleImageView cornerPostImageView;
        @BindView(R.id.cornerPostTxtHeader) TextView cornerPostTxtHeader;
        @BindView(R.id.cornerPostTxtDescription) TextView cornerPostTxtDescription;
        @BindView(R.id.cornerPostTxtDate) TextView cornerPostTxtDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
