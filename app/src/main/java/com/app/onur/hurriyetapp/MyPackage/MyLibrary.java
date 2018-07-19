package com.app.onur.hurriyetapp.MyPackage;


import android.content.Context;
import android.net.ConnectivityManager;

public class MyLibrary {

    private static Context context;

    public MyLibrary(Context c){
        this.context = c;
    }

    public static boolean InternetControl(){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() &&
                connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }else{
            return  false;
        }

    }
}
