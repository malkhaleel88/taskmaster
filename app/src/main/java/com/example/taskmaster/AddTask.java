package com.example.taskmaster;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;


public class AddTask extends AppCompatActivity {

    int counter;
    private static final String TAG = "AddTask";

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

//                TaskOld taskOld = new TaskOld(title, body, state);
//                AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(taskOld);

                dataStore(title, body, state);

                TextView tasks = findViewById(R.id.textView7);
                counter++;
                tasks.setText(String.valueOf(counter));
                Context context = getApplicationContext();
                Toast.makeText(context, "Submitted!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dataStore(String title, String body, String state) {
        Task task = Task.builder().title(title).state(state).body(body).build();

        Amplify.DataStore.save(task, result -> {
            Log.i(TAG, "Task Saved");
        }, error -> {
            Log.i(TAG, "Task Not Saved");
        });

    }
}