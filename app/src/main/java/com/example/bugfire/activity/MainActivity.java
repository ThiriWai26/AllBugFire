package com.example.bugfire.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.bugfire.R;
import com.example.bugfire.ui.dashboard.DashboardFragment;
import com.example.bugfire.ui.home.HomeFragment;
import com.example.bugfire.ui.message.MessageFragment;
import com.example.bugfire.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_messages)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController);
//        NavigationUI.setupWithNavController(navView , navController);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                Fragment fragment = new HomeFragment();
                loadFragment(fragment);
                Toast.makeText(getApplicationContext(), "New Feeds",Toast.LENGTH_LONG).show();
                return true;

            case R.id.navigation_dashboard:
                Fragment fragment1 = new DashboardFragment();
                Toast.makeText(getApplicationContext(), "Video",Toast.LENGTH_LONG).show();
                loadFragment(fragment1);
                return true;

            case R.id.navigation_notifications:
                Fragment fragment2 = new NotificationsFragment();
                Toast.makeText(getApplicationContext(), "Topic",Toast.LENGTH_LONG).show();
                loadFragment(fragment2);
                return true;

            case R.id.navigation_messages:
                Fragment fragment3 = new MessageFragment();
                Toast.makeText(getApplicationContext(), "Articles",Toast.LENGTH_LONG).show();
                loadFragment(fragment3);
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
