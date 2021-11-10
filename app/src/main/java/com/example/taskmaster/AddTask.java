package com.example.taskmaster;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;


public class AddTask extends AppCompatActivity {

    int counter;
    private static final String TAG = "AddTask";

    private TaskDao taskDao;

    private String teamId = "";

    private final List<Team> teams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getTeamsDataFromCloud();

        Spinner teamsList = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.teams_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamsList.setAdapter(adapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor preferenceEditor = sharedPreferences.edit();

        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task_List")
                .allowMainThreadQueries().build();
        taskDao = database.taskDao();

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
                Spinner teamSpinner = (Spinner) findViewById(R.id.spinner3);
                String teamName = teamSpinner.getSelectedItem().toString();

                preferenceEditor.putString("teamName", teamName);
                preferenceEditor.apply();

                TaskOld newTask = new TaskOld(title, body, state);
                taskDao.insertTask(newTask);


                addTaskToCloud(title, body, state, new Team(getTeamId(teamName), teamName));

            }
        });

        TextView tasks = findViewById(R.id.textView7);
        counter++;
        tasks.setText(String.valueOf(counter));
        Context context = getApplicationContext();
        Toast.makeText(context, "Submitted!", Toast.LENGTH_LONG).show();
    }

//                Task task = Task.builder()
//                        .title(title)
//                        .body(body)
//                        .state(state)
//                        .build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(task),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );

    public void addTaskToCloud(String taskTitle, String taskBody, String taskState, Team team) {
        com.amplifyframework.datastore.generated.model.Task task = com.amplifyframework.datastore.generated.model.Task.builder()
                .title(taskTitle)
                .body(taskBody)
                .state(taskState)
                .team(team)
                .build();

        Amplify.API.mutate(ModelMutation.create(task),
                success -> Log.i(TAG, "Saved item: " + task.getTitle()),
                error -> Log.e(TAG, "Could not save item to API", error));

        Toast toast = Toast.makeText(this, "Task added!", Toast.LENGTH_LONG);
        toast.show();
    }

    private void getTeamsDataFromCloud() {
        Amplify.API.query(ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        teams.add(team);
                        Log.i(TAG, "TEAM ID FROM CLOUD IS:  " + team.getTeamName() + "  " + team.getId());
                    }
                },
                error -> Log.e(TAG, "Failed to get TEAM ID FROM CLOUD: " + error.toString())
        );
    }


    public String getTeamId(String teamName) {
        for (Team team : teams) {
            if (team.getTeamName().equals(teamName)) {
                return team.getId();
            }
        }
        return "";
    }
}

