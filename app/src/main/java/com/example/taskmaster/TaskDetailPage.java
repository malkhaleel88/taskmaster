package com.example.taskmaster;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String titleName = sharedPreferences.getString("title","title");
        String bodyName = sharedPreferences.getString("body","body");
        String stateName = sharedPreferences.getString("state","state");


        TextView title = findViewById(R.id.textView8);
        TextView body = findViewById(R.id.textView10);
        TextView state = findViewById(R.id.textView14);

        title.setText(titleName);
        body.setText(bodyName);
        state.setText(stateName);
    }

}