package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.squareup.picasso.Picasso;

public class TaskDetailPage extends AppCompatActivity {

    private String fileURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String titleName = sharedPreferences.getString("title","title");
        String bodyName = sharedPreferences.getString("body","body");
        String stateName = sharedPreferences.getString("state","state");
        String filename = sharedPreferences.getString("Filename","");

        TextView title = findViewById(R.id.textView8);
        TextView body = findViewById(R.id.textView10);
        TextView state = findViewById(R.id.textView14);


        title.setText(titleName);
        body.setText(bodyName);
        state.setText(stateName);



        TextView fileLinkDetail = findViewById(R.id.fileLinkDetail);
        fileLinkDetail.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(fileURL));
            startActivity(i);
        });

        if (filename!= null) {

            Amplify.Storage.getUrl(
                    filename,
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully generated: " + result.getUrl());
                        runOnUiThread(() -> {
                            if (filename.endsWith("png")
                                    || filename.endsWith("jpg")
                                    || filename.endsWith("jpeg")
                                    || filename.endsWith("gif")) {
                                ImageView taskImageDetail = findViewById(R.id.taskImageDetail);
                                System.out.println(result.getUrl());
                                Picasso.get().load(String.valueOf(result.getUrl())).into(taskImageDetail);

                                taskImageDetail.setVisibility(View.VISIBLE);
                            }else{
                                fileURL = String.valueOf(result.getUrl());
//                                String link = "<a href=\""+ result.getUrl() + "\">Download the linked file</a>";
                                fileLinkDetail.setVisibility(View.VISIBLE);
                            }
                        });
                    },
                    error -> Log.e("MyAmplifyApp", "URL generation failure", error)
            );
        }

    }



}