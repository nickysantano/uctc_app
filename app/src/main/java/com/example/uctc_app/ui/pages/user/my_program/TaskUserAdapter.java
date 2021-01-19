package com.example.uctc_app.ui.pages.user.my_program;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.repository.login.TaskRepository;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskUserAdapter extends RecyclerView.Adapter<TaskUserAdapter.ViewHolder> {


    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;
    private int actionPlan_id;
    private String program_id;
    TaskRepository repository;

    public TaskUserAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList, int actionPlan_id, String program_id) {
        this.taskList = taskList;
        this.actionPlan_id = actionPlan_id;
        this.program_id = program_id;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_task_adapter, parent, false);
        return new TaskUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskUserAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        Log.d(TAG, "onBindViewHolder: " + task.getName());

        repository = TaskRepository.getInstance(SharedPreferenceHelper.getInstance(context).getAccessToken());

        holder.taskTtl.setText(task.getName());
        holder.taskDate.setText(task.getDate());

        if (task.getStatus().equalsIgnoreCase("0")){
            holder.taskStatus.setText("On-going");
        }else if(task.getStatus().equalsIgnoreCase("1")){
            holder.taskStatus.setText("Finished");
        }

        holder.itemView.setOnClickListener(v -> {
            TaskFragmentDirections.ActionTaskFragmentToDetailTask actionTaskFragmentToDetailTask = TaskFragmentDirections.actionTaskFragmentToDetailTask(task);
            Navigation.findNavController(v).navigate(actionTaskFragmentToDetailTask);
//            ToDoListStaffFragmentDirections.ActionToDoListToDetailToDoListStaff actionToDoListToDetailToDoListStaff = ToDoListStaffFragmentDirections.actionToDoListToDetailToDoListStaff(task);
//            Navigation.findNavController(v).navigate(actionToDoListToDetailToDoListStaff);
        });

        holder.update.setOnClickListener(v -> {
//            TaskRepository repository = TaskRepository.getInstance(SharedPreferenceHelper.getInstance(context).getAccessToken());
//            repository.updateTask(task.getTask_id());
//            Log.d("UPDATE", "PLEASEEEEE");

            TaskFragmentDirections.ActionTaskUserToUpdateTaskUser actionTaskUserToUpdateTaskUser =
                    TaskFragmentDirections.actionTaskUserToUpdateTaskUser(actionPlan_id, program_id, task);
            Navigation.findNavController(v).navigate(actionTaskUserToUpdateTaskUser);
        });
//
        holder.delete.setOnClickListener(v -> {
            repository.deleteTask(task.getTask_id());
            Log.d("DELETEEEEEEEEEEE", "PLEASEEEEE");

            TaskFragmentDirections.ActionTaskUserFragmentSelf actionTaskUserFragmentSelf = TaskFragmentDirections.actionTaskUserFragmentSelf(actionPlan_id, program_id);
            Navigation.findNavController(v).navigate(actionTaskUserFragmentSelf);
        });
        if (task.getStatus().equalsIgnoreCase("0")){
            holder.on.setEnabled(false);
            holder.on.setVisibility(View.GONE);
            holder.off.setEnabled(true);
            holder.off.setVisibility(View.VISIBLE);
        }
        else{
            holder.off.setEnabled(false);
            holder.off.setVisibility(View.GONE);
            holder.on.setEnabled(true);
            holder.on.setVisibility(View.VISIBLE);
        }
        holder.off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.updateTask(task.getTask_id(),new Task(task.getName(),"1",task.getDescription(),task.getDate(),task.getAction_plan(),task.getPic()));
                holder.off.setEnabled(false);
                holder.off.setVisibility(View.GONE);
                holder.on.setEnabled(true);
                holder.on.setVisibility(View.VISIBLE);
            }
        });
        holder.on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.updateTask(task.getTask_id(),new Task(task.getName(),"0",task.getDescription(),task.getDate(),task.getAction_plan(),task.getPic()));
                holder.on.setEnabled(false);
                holder.on.setVisibility(View.GONE);
                holder.off.setEnabled(true);
                holder.off.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTtl, taskDate, taskStatus;
        private FloatingActionButton update, delete;
        Button on, off;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTtl = itemView.findViewById(R.id.task_title);
            taskDate = itemView.findViewById(R.id.lbl_date_task_user);
            taskStatus = itemView.findViewById(R.id.lbl_status_task);
            update = itemView.findViewById(R.id.btn_update_task);
            delete = itemView.findViewById(R.id.btn_delete_task);
            on = itemView.findViewById(R.id.btn_status_on);
            off = itemView.findViewById(R.id.btn_status_off);
        }
    }
}
