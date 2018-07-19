package com.app.onur.hurriyetapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.app.onur.hurriyetapp.MyPackage.MyLibrary;
import com.app.onur.hurriyetapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InternetActivity extends AppCompatActivity {

    @BindView(R.id.btnOK)
    Button btnOk;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    @OnClick(R.id.btnOK) void btnOksubmit(){

        if(MyLibrary.InternetControl()){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    @OnClick(R.id.btnCancel) void btnCancelSubmit(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        ButterKnife.bind(this);
    }
}
