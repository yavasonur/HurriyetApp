package com.app.onur.hurriyetapp.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.app.onur.hurriyetapp.Adapters.ViewPagerAdapter;
import com.app.onur.hurriyetapp.Fragments.FavoriteFragment;
import com.app.onur.hurriyetapp.Fragments.HomeFragment;
import com.app.onur.hurriyetapp.Fragments.InfoFragment;
import com.app.onur.hurriyetapp.Fragments.PersonFragment;
import com.app.onur.hurriyetapp.MyPackage.MyLibrary;
import com.app.onur.hurriyetapp.R;


import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout) TabLayout tabLayout;
    @BindView(R.id.main_tab_content) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLibrary myLibrary = new MyLibrary(getBaseContext());

        if(!MyLibrary.InternetControl()){

            Intent i = new Intent(this,InternetActivity.class);
            startActivity(i);
            finish();
        }else{

            ButterKnife.bind(this);


            int[] icons = {R.drawable.tab_home,R.drawable.tab_person, R.drawable.tab_favorite, R.drawable.tab_info};

            setupViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);

            for (int i = 0; i < icons.length; i++) {
                tabLayout.getTabAt(i).setIcon(icons[i]);
            }
            tabLayout.getTabAt(0).select();
        }




    }
    private void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.insertNewFragment(new HomeFragment());
        viewPagerAdapter.insertNewFragment(new PersonFragment());
        viewPagerAdapter.insertNewFragment(new FavoriteFragment());
        viewPagerAdapter.insertNewFragment(new InfoFragment());

        viewPager.setAdapter(viewPagerAdapter);

    }

}
