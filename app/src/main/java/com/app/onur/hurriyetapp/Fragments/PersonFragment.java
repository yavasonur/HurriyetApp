package com.app.onur.hurriyetapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.onur.hurriyetapp.Adapters.AuthorRvAdapter;
import com.app.onur.hurriyetapp.Model.Author;
import com.app.onur.hurriyetapp.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.rey.material.widget.ProgressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonFragment extends Fragment {

    @BindView(R.id.recyclerViewAuthor) RecyclerView authorRecyclerView;
    @BindView(R.id.circular_progress_Author) ProgressView authorCircularProgress;

    ArrayList<Author> authorList = new ArrayList<>();
    AuthorRvAdapter authorRvAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person,container,false);
        ButterKnife.bind(this,view);
        jsonCall();
        return view;
    }

    public void jsonCall(){

        final Gson gson = new Gson();

        String url = "https://api.hurriyet.com.tr/v1/writers";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        authorList.clear();

                        JsonArray jsonArray=new JsonParser().parse(response).getAsJsonArray();

                        for(int i=0; i < jsonArray.size(); i++)
                        {
                            Author authorModel = gson.fromJson(jsonArray.get(i),Author.class);

                            authorList.add(authorModel);
                        }

                        authorCircularProgress.setVisibility(View.INVISIBLE);

                        authorRvAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {/*hata mesajını alacağımız yer*/

                Log.e("responseErr",error.getMessage());

                authorCircularProgress.setVisibility(View.INVISIBLE);
            }
        })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("accept","application/json");
                params.put("apikey","156e652d34a24e9db998b8596c0de760");
                return params;
            }
        };

        requestQueue.add(stringRequest);
        //VolleyApplication.getInstance().getRequestQueue().add(stringRequest);

        authorRvAdapter = new AuthorRvAdapter(getContext(),authorList);
        authorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        authorRecyclerView.setAdapter(authorRvAdapter);

    }
}
