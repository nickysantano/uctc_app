package com.example.uctc_app.ui.pages.staff.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.pages.user.home.HomeUserFragmentDirections;

import java.util.List;

public class TaskHomeStaffAdapter extends RecyclerView.Adapter<TaskHomeStaffAdapter.ViewHolder> {


    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;

    public TaskHomeStaffAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskHomeStaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_task_home_staff_adapter, parent, false);
        return new TaskHomeStaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHomeStaffAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        Log.d(TAG, "onBindViewHolder: " + task.getName());

        holder.taskTtl.setText(task.getName());
        holder.taskDate.setText(task.getDate());
        holder.itemView.setOnClickListener(v -> {
            HomeUserFragmentDirections.ActionHomeToDetailTask actionHomeToDetailTask = HomeUserFragmentDirections.actionHomeToDetailTask(task);
            Navigation.findNavController(v).navigate(actionHomeToDetailTask);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTtl, taskDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTtl = itemView.findViewById(R.id.task_title);
            taskDate = itemView.findViewById(R.id.lbl_date_task_user);
        }
    }


}