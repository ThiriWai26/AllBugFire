package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bugfire.R;
import com.example.bugfire.activity.AnotherHomenaviActivity;
import com.example.bugfire.activity.MainActivity;
import com.example.bugfire.ui.dashboard.DashboardFragment;
import com.example.bugfire.ui.home.HomeFragment;
import com.example.bugfire.ui.message.MessageFragment;
import com.example.bugfire.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeNavigation extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    
    private TextView txttitle;
    
    public HomeNavigation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_navigation, container, false);

        BottomNavigationView navView = view.findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        txttitle = view.findViewById(R.id.tvTitle);
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Intent intent = new Intent(getContext(), AnotherHomenaviActivity.class);
                intent.putExtra("fragment","Stories");
                Log.e("title","stories");
                startActivity(intent);
                return true;

            case R.id.navigation_dashboard:
                Intent intent1 = new Intent(getContext(), AnotherHomenaviActivity.class);
                intent1.putExtra("fragment","Videos");
                startActivity(intent1);
                return true;

            case R.id.navigation_notifications:
                Intent intent2 = new Intent(getContext(), AnotherHomenaviActivity.class);
                intent2.putExtra("fragment","Topics");
                startActivity(intent2);
                return true;

            case R.id.navigation_messages:
                Intent intent4 = new Intent(getContext(), AnotherHomenaviActivity.class);
                intent4.putExtra("fragment","Articles");
                startActivity(intent4);
                return true;
        }
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedFragment).commit();
        return true;
    }

}
