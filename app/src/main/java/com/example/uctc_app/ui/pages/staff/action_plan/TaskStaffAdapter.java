package com.example.uctc_app.ui.pages.staff.action_plan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramStaffFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TaskStaffAdapter extends RecyclerView.Adapter<TaskStaffAdapter.ViewHolder> {


    private static final String TAG = "TaskAdapter";
    private Context context;
    private List<Task> taskList;
    private int actionPlan_id;
    private String program_id;

    public TaskStaffAdapter(Context context) {
        this.context = context;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
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

//        actionPlan_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getActionPlanId();
//        program_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getProgramId();

        holder.taskTtl.setText(task.getName());
        holder.taskDate.setText(task.getDate());

        holder.itemView.setOnClickListener(v -> {
            ToDoListStaffFragmentDirections.ActionToDoListToDetailToDoListStaff actionToDoListToDetailToDoListStaff = ToDoListStaffFragmentDirections.actionToDoListToDetailToDoListStaff(task);
            Navigation.findNavController(v).navigate(actionToDoListToDetailToDoListStaff);
        });

        holder.delete.setOnClickListener(v -> {
            ProgramRepository repository = ProgramRepository.getInstance(SharedPreferenceHelper.getInstance(context).getAccessToken());
            repository.deleteProgram(task.getTask_id());

//            ToDoListStaffFragmentDirections.ActionToDoListStaffSelf actionToDoListStaffSelf = ToDoListStaffFragmentDirections.actionToDoListStaffSelf();
//            Navigation.findNavController(v).navigate(actionToDoListStaffSelf);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTtl, taskDate;
        FloatingActionButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTtl = itemView.findViewById(R.id.task_title);
            taskDate = itemView.findViewById(R.id.lbl_date_task_user);
            delete = itemView.findViewById(R.id.btn_delete_task);
        }
    }
}
