package com.example.uctc_app.ui.pages.user.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_task_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        Log.d(TAG, "onBindViewHolder: " + task.getName());

        holder.taskTtl.setText(task.getName());

        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTtl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTtl = itemView.findViewById(R.id.task_title);
        }
    }
}
