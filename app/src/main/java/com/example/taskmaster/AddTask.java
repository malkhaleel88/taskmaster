package com.example.taskmaster;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


public class AddTask extends AppCompatActivity {

    private static final String TAG = "AddTask";


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        try {
                            onChooseFile(result);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

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

                findViewById(R.id.btnUploadFile).setOnClickListener(view -> {
                    Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                    chooseFile.setType("*/*");
                    chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                    someActivityResultLauncher.launch(chooseFile);
                });

                String fileNameIfThere = uploadedFileName == null ? "" : uploadedFileName;

                dataStore(title, body, state, id);

            }
        });

        Context context = getApplicationContext();
        Toast.makeText(context, "Submitted!", Toast.LENGTH_LONG).show();

    }


    public void dataStore(String taskTitle, String taskBody, String taskState, String id) {
        Task task = Task.builder().teamId(id).title(taskTitle).body(taskBody).state(taskState)
                .fileName(fileNameIfThere).build();

        Amplify.API.mutate(ModelMutation.create(task),
                success -> Log.i(TAG, "Saved to DynamoDB"),
                error -> Log.i(TAG, "Error Saving to DynamoDB"));

        Toast toast = Toast.makeText(this, "Task added!", Toast.LENGTH_LONG);
        toast.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void onChooseFile(ActivityResult activityResult) throws IOException {

        Uri uri = null;
        if (activityResult.getData() != null) {
            uri = activityResult.getData().getData();
        }
        assert uri != null;
        uploadedFileName = new Date().toString() + "." + getMimeType(getApplicationContext(),uri);

        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFile");

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            FileUtils.copy(inputStream, new FileOutputStream(uploadFile));
        } catch (Exception exception) {
            Log.e("onChooseFile", "onActivityResult: file upload failed" + exception.toString());
        }

        Amplify.Storage.uploadFile(
                uploadedFileName,
                uploadFile,
                success -> Log.i("onChooseFile", "uploadFileToS3: succeeded " + success.getKey()),
                error -> Log.e("onChooseFile", "uploadFileToS3: failed " + error.toString())
        );
    }

    public static String getMimeType(Context context, Uri uri) {
        String extension;

        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }
}

