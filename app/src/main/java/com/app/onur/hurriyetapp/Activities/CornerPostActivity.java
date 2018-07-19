package com.app.onur.hurriyetapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.onur.hurriyetapp.Adapters.CornerPostRvAdapter;
import com.app.onur.hurriyetapp.Model.CornerPost;
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

public class CornerPostActivity extends AppCompatActivity {

    @BindView(R.id.circular_progress_cornerPost) ProgressView circularProgressCornerPost;
    @BindView(R.id.recyclerViewCornerPost) RecyclerView recyclerViewCornerPost;

    ArrayList<CornerPost> cornerPosts = new ArrayList<>();
    CornerPostRvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corner_post);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String writerId = extras.getString("writerId");

        jsonCall(writerId);
    }

    public void jsonCall(String writerId){

        final Gson gson = new Gson();

        String url = "https://api.hurriyet.com.tr/v1/columns?%24filter=WriterId%20eq%20'" + writerId + "'";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        cornerPosts.clear();

                        JsonArray jsonArray=new JsonParser().parse(response).getAsJsonArray();

                        for(int i=0; i < jsonArray.size(); i++)
                        {
                            CornerPost cornerPostModel = gson.fromJson(jsonArray.get(i),CornerPost.class);

                            cornerPosts.add(cornerPostModel);
                        }

                        circularProgressCornerPost.setVisibility(View.INVISIBLE);

                        rvAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {/*hata mesajını alacağımız yer*/

                Log.e("responseErr",error.getMessage());

                circularProgressCornerPost.setVisibility(View.INVISIBLE);
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

        rvAdapter = new CornerPostRvAdapter(getApplicationContext(),cornerPosts);
        recyclerViewCornerPost.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewCornerPost.setAdapter(rvAdapter);

    }
}
