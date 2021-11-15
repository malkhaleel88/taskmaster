package com.example.taskmaster;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
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

                RadioButton b1=findViewById(R.id.radioButtonTeam1);
                RadioButton b2=findViewById(R.id.radioButtonTeam2);
                RadioButton b3=findViewById(R.id.radioButtonTeam3);

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

                dataStore(title, body, state, id);

            }
        });

        TextView tasks = findViewById(R.id.textView7);
        counter++;
        tasks.setText(String.valueOf(counter));
        Context context = getApplicationContext();
        Toast.makeText(context, "Submitted!", Toast.LENGTH_LONG).show();
    }


    public void dataStore(String taskTitle, String taskBody, String taskState, String id) {
        Task task = Task.builder().teamId(id).title(taskTitle).body(taskBody).state(taskState).build();

        Amplify.API.mutate(ModelMutation.create(task),
                success -> Log.i(TAG, "Saved to DynamoDB"),
                error -> Log.i(TAG, "Error Saving to DynamoDB"));

        Toast toast = Toast.makeText(this, "Task added!", Toast.LENGTH_LONG);
        toast.show();
    }


}

