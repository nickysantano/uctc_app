package com.example.uctc_app.ui.pages.staff.action_plan;

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
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.repository.login.TaskRepository;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramStaffFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;

public class TaskStaffAdapter extends RecyclerView.Adapter<TaskStaffAdapter.ViewHolder> {

    @BindView(R.id.btn_status_on)
    Button status_done;

    @BindView(R.id.btn_status_off)
    Button status_ongoing;

    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;
    private int actionPlan_id;
    private String program_id;
    Task task;
    TaskRepository repository ;
    private boolean isStatus;

    public TaskStaffAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList, int actionPlan_id, String program_id) {
        this.taskList = taskList;
        this.actionPlan_id = actionPlan_id;
        this.program_id = program_id;
        this.isStatus = isStatus;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskStaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_task_adapter, parent, false);
        return new TaskStaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskStaffAdapter.ViewHolder holder, int position) {
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

        if(isStatus){
            if (task.getStatus().equals("1")) {
                status_ongoing.setVisibility(View.GONE);
                status_done.setVisibility(View.VISIBLE);
            } else if ((task.getStatus().equals("0"))){
                status_ongoing.setVisibility(View.VISIBLE);
                status_done.setVisibility(View.GONE);
            }
        }


        holder.itemView.setOnClickListener(v -> {
            ToDoListStaffFragmentDirections.ActionToDoListToDetailToDoListStaff actionToDoListToDetailToDoListStaff =
                    ToDoListStaffFragmentDirections.actionToDoListToDetailToDoListStaff(task);
            Navigation.findNavController(v).navigate(actionToDoListToDetailToDoListStaff);
        });

        holder.update.setOnClickListener(v -> {
//            TaskRepository repository = TaskRepository.getInstance(SharedPreferenceHelper.getInstance(context).getAccessToken());
//            repository.updateTask(task.getTask_id());
//            Log.d("UPDATE", "PLEASEEEEE");

            ToDoListStaffFragmentDirections.ActionToDoListStaffToUpdateTaskStaff actionToDoListStaffToUpdateTaskStaff =
                    ToDoListStaffFragmentDirections.actionToDoListStaffToUpdateTaskStaff(actionPlan_id, program_id, task);
            Navigation.findNavController(v).navigate(actionToDoListStaffToUpdateTaskStaff);
        });

        holder.delete.setOnClickListener(v -> {
            repository.deleteTask(task.getTask_id());
            Log.d("DELETEEEEEEEEEEE", "PLEASEEEEE");

            ToDoListStaffFragmentDirections.ActionToDoListStaffSelf actionToDoListStaffSelf = ToDoListStaffFragmentDirections.actionToDoListStaffSelf(actionPlan_id, program_id);
            Navigation.findNavController(v).navigate(actionToDoListStaffSelf);
        });
        if (task.getStatus().equalsIgnoreCase("1")){
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
        FloatingActionButton update, delete;
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

    private void showStatus(Boolean state) {

    }
}
