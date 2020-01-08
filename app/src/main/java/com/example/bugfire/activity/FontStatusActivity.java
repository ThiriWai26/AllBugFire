package com.example.bugfire.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bugfire.R;

public class FontStatusActivity extends AppCompatActivity {

    public static String userFont = null;
    private SharedPreferences pref;// 0 - for private mode
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_status);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        userFont = pref.getString("font", null);

        if (userFont == null) {
            chooseZawgyiorUnicode();
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void chooseZawgyiorUnicode() {
        Log.e("Rabbit", "success");
        final boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun) {
            final String[] listItems = getResources().getStringArray(R.array.dialog_single_choice_array);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            userFont = "z";
                            break;

                        case 1:
                            userFont = "u";
                            break;
                    }
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    editor.putString("font", userFont);
                    editor.apply();
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

}
