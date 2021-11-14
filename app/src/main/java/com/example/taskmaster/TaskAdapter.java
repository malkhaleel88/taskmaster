package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> task = new ArrayList<>();


    public TaskAdapter(List<Task> task) {
        this.task = task;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Task item = task.get(position);
        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());
        holder.state.setText(item.getState());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("my Adapter", "Element "+ holder.getAdapterPosition() + " clicked");

                String Task1 = holder.title.getText().toString();
                editor.putString("TaskName",Task1);
                editor.apply();
                Intent gotToStd = new Intent(context, TaskDetailPage.class);
                context.startActivity(gotToStd);
            }
        });

    }

    @Override
    public int getItemCount() {
        return task.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView body;
        private TextView state;
        public LinearLayout linearLayout;

        ViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.textView11);
            body = itemView.findViewById(R.id.textView12);
            state = itemView.findViewById(R.id.textView13);
            linearLayout = itemView.findViewById(R.id.layout);

        }

    }
}
