package com.example.uctc_app.ui.pages.user.home;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.pages.staff.action_plan.TaskStaffAdapter;
import com.example.uctc_app.ui.pages.user.my_program.TaskFragmentDirections;
import com.example.uctc_app.ui.pages.user.my_program.TaskViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;

import butterknife.BindView;

public class TaskHomeAdapter extends RecyclerView.Adapter<TaskHomeAdapter.ViewHolder> {


    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;

    public TaskHomeAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_task_home_adapter, parent, false);
        return new TaskHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHomeAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        Log.d(TAG, "onBindViewHolder: " + task.getName());

        holder.taskTtl.setText(task.getName());
        holder.taskDate.setText(task.getDate());

        if (task.getStatus().equalsIgnoreCase("0")){
            holder.taskStatus.setText("On-going");
        }else if(task.getStatus().equalsIgnoreCase("1")){
            holder.taskStatus.setText("Finished");
        }

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

        private TextView taskTtl, taskDate, taskStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTtl = itemView.findViewById(R.id.task_title);
            taskDate = itemView.findViewById(R.id.lbl_date_task_user);
            taskStatus = itemView.findViewById(R.id.lbl_status_task);
        }
    }
}