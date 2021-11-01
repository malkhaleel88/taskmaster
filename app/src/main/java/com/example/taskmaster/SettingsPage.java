package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.button8).setOnClickListener(view -> {
            TextView text = findViewById(R.id.editTextTextPersonName3);

            String name =text.getText().toString();

            editor.putString("UserName", name);

            editor.apply();

            Context context = getApplicationContext();
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show();

        });

    }
}