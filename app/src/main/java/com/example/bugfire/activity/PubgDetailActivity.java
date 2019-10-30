package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;

public class PubgDetailActivity extends AppCompatActivity {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvtime, tvabout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubg_detail);

        initActivity();
    }

    private void initActivity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvtime = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);
    }

}
