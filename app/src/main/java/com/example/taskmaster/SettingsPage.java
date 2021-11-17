package com.example.taskmaster;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.core.Amplify;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        recordEvent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        findViewById(R.id.button8).setOnClickListener(view -> {
            TextView text = findViewById(R.id.editTextTextPersonName3);
            String name = text.getText().toString();

            RadioButton b1=findViewById(R.id.radioButton1);
            RadioButton b2=findViewById(R.id.radioButton2);
            RadioButton b3=findViewById(R.id.radioButton3);


            String id = null;
            if(b1.isChecked()){
                id="1";
            }
            else if(b2.isChecked()){
                id="2";
            }
            else if(b3.isChecked()){
                id="3";
            }


            editor.putString("Team", id);
            editor.putString("UserName", name);
            editor.apply();

            Context context = getApplicationContext();
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show();

        });

    }

    private void recordEvent(){
        AnalyticsEvent event = AnalyticsEvent.builder()
                .name("Launch Setting Activity")
                .addProperty("Channel", "SMS")
                .addProperty("Successful", true)
                .addProperty("ProcessDuration", 792)
                .addProperty("UserAge", 120.3)
                .build();

        Amplify.Analytics.recordEvent(event);
    }

}