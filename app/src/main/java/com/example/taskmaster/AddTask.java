package com.example.taskmaster;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button submit = findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskTitle = findViewById(R.id.taskTitleInput);
                String title = taskTitle.getText().toString();

                EditText taskBody = findViewById(R.id.taskBodyInput);
                String body = taskBody.getText().toString();

                EditText taskState = findViewById(R.id.taskStateInput);
                String state = taskState.getText().toString();

                Task task = new Task(title, body, state);
                AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(task);

                TextView tasks = findViewById(R.id.textView7);
                counter++;
                tasks.setText(String.valueOf(counter));
                Context context = getApplicationContext();
                Toast.makeText(context, "Submitted!", Toast.LENGTH_LONG).show();
            }
        });
    }
}