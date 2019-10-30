package com.example.bugfire.activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.legacy.app.ActionBarDrawerToggle;

import com.example.bugfire.R;
import com.example.bugfire.ui.dashboard.DashboardFragment;
import com.example.bugfire.ui.home.HomeFragment;
import com.example.bugfire.ui.message.MessageFragment;
import com.example.bugfire.ui.notifications.NotificationsFragment;
import com.google.android.material.navigation.NavigationView;


public class AnotherHomenaviActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String fragmentName;
    private TextView txttitle;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_homenavi);

        txttitle = findViewById(R.id.tvTitle);

        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        init();
    }

    private void init() {


        Intent intent = getIntent();
        fragmentName = intent.getStringExtra("fragment");

        Log.e("fragmentName",fragmentName);

        if(fragmentName.equals("Stories")){
            loadFragment(new HomeFragment());
            txttitle.setText("Stories");
        }
        if(fragmentName.equals("Videos")){
            loadFragment(new DashboardFragment());
            txttitle.setText("Videos");
        }
        if(fragmentName.equals("Topics")){
            loadFragment(new NotificationsFragment());
            txttitle.setText("Topics");
        }
        if(fragmentName.equals("Articles")){
            loadFragment(new MessageFragment());
            txttitle.setText("Articles");
        }
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
