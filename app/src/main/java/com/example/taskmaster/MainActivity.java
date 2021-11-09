package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    boolean configured = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addTask = findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddTask = new Intent(MainActivity.this, AddTask.class);
                startActivity(intentAddTask);
            }
        });
        Button allTasks = findViewById(R.id.button2);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAllTasks = new Intent(MainActivity.this, AllTasks.class);
                startActivity(intentAllTasks);
            }
        });

        findViewById(R.id.button4).setOnClickListener(view -> {
            Intent intentSetting = new Intent(MainActivity.this, SettingsPage.class);
            startActivity(intentSetting);
        });

        Button buttonTask1 = findViewById(R.id.button5);
        buttonTask1.setOnClickListener(view -> {
            Intent intentTaskDetails = new Intent(MainActivity.this, TaskDetailPage.class);
            String task1 = buttonTask1 .getText().toString();
            intentTaskDetails.putExtra("title", task1);
            startActivity(intentTaskDetails);
        });

        Button buttonTask2 = findViewById(R.id.button6);
        buttonTask2.setOnClickListener(view -> {
            Intent intentTaskDetails = new Intent(MainActivity.this, TaskDetailPage.class);
            String task2 = buttonTask2 .getText().toString();
            intentTaskDetails.putExtra("title", task2);
            startActivity(intentTaskDetails);
        });

        Button buttonTask3 = findViewById(R.id.button7);
        buttonTask3.setOnClickListener(view -> {
            Intent intentTaskDetails = new Intent(MainActivity.this, TaskDetailPage.class);
            String task3 = buttonTask3 .getText().toString();
            intentTaskDetails.putExtra("title", task3);
            startActivity(intentTaskDetails);
        });

//        ArrayList<Task> taskData = new ArrayList<>();
//
//        taskData.add(new Task("Mercedes", "German Cars Company", "new"));
//        taskData.add(new Task("Ford", "American Cars Company", "assigned" ));
//        taskData.add(new Task("Hyundai", "Korean Cars Company", "in progress"));
//        taskData.add(new Task("Toyota", "Japanese Cars Company", "complete"));

//        List<TaskOld> taskOldData = AppDatabase.getInstance(this).taskDao().getAll();
        if(configured) {
            configureAmplify();
        }

        List<Task> tasks = new ArrayList<>();
        tasks = GetData();

        RecyclerView allTasksRecyclerView = findViewById(R.id.recycleViewId);

        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Task> finalTasks = tasks;
        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks, new TaskAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intentTaskDetails = new Intent(getApplicationContext(), TaskDetailPage.class);
                intentTaskDetails.putExtra("title", finalTasks.get(position).getTitle());
                intentTaskDetails.putExtra("body", finalTasks.get(position).getBody());
                intentTaskDetails.putExtra("state", finalTasks.get(position).getState());
                startActivity(intentTaskDetails);

            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString("UserName","User");

        TextView title = findViewById(R.id.textView);
        title.setText(userName + "'s Tasks");
    }

    private void configureAmplify() {
        configured = false;
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e(TAG, "Could not initialize Amplify", error);
        }}
    private  List<Task> GetData(){
        List<Task> foundTask = new ArrayList<>();

        Amplify.DataStore.query(
                Task.class,
                queryMatches -> {
                    while (queryMatches.hasNext()) {
                        Log.i(TAG, "Successful Query, Found Tasks.");
                        foundTask.add(queryMatches.next());
                    }
                },
                error -> {
                    Log.i(TAG,  "Error Retrieving Task", error);
                });
        return foundTask;
    }
}