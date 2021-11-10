package com.example.taskmaster;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        Spinner teamsList = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.teams_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamsList.setAdapter(adapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.button8).setOnClickListener(view -> {
            TextView text = findViewById(R.id.editTextTextPersonName3);
            String name =text.getText().toString();

            Spinner teamSpinner = (Spinner) findViewById(R.id.spinner2);
            String teamName = teamSpinner.getSelectedItem().toString();

            editor.putString("UserName", name);
            editor.putString("teamName", teamName);
            editor.apply();

            Context context = getApplicationContext();
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show();

        });

    }
}