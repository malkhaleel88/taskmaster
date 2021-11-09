package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final List<TaskOld> task;
    private OnTaskItemClickListener listener;


    public TaskAdapter(List<TaskOld> taskMasterItem, OnTaskItemClickListener listener) {
        this.task = taskMasterItem;
        this.listener = listener;
    }


    public interface OnTaskItemClickListener {
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task, parent,false);

        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskOld item = task.get(position);
        holder.title.setText(item.title);
        holder.body.setText(item.body);
        holder.state.setText(item.state);

    }

    @Override
    public int getItemCount() {
        return task.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView body;
        private TextView state ;
        ViewHolder(@NonNull View itemView, OnTaskItemClickListener listener){
            super(itemView);

            title = itemView.findViewById(R.id.textView11);
            body = itemView.findViewById(R.id.textView12);
            state = itemView.findViewById(R.id.textView13);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition());

                }
            });
        }

    }
}
