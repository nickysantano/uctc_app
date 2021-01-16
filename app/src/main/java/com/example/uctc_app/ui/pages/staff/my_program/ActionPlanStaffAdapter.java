package com.example.uctc_app.ui.pages.staff.my_program;

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
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanAdapter;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentDirections;

import java.util.List;

public class ActionPlanStaffAdapter extends RecyclerView.Adapter<ActionPlanStaffAdapter.ViewHolder> {

    private Context context;
    private List<ActionPlan> actionList;
    private String program_id;

    public ActionPlanStaffAdapter(Context context) {
        this.context = context;
    }

    public void setActionList(List<ActionPlan> actionList, String program_id) {
        this.actionList = actionList;
        this.program_id = program_id;
        Log.d("SETTING ActionList", getItemCount() + "actions");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActionPlanStaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_action_plan_staff_adapter, parent, false);
        return new ActionPlanStaffAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ActionPlanStaffAdapter.ViewHolder holder, int position) {
        ActionPlan actionPlan = actionList.get(position);
        Log.d("Hello", "" + actionPlan.getName());

        holder.name.setText(actionPlan.getName());

        holder.itemView.setOnClickListener(view -> {
            ActionPlanStaffFragmentDirections.ActionActionPlanStaffToToDoList actionActionPlanStaffToToDoList =
                    ActionPlanStaffFragmentDirections.actionActionPlanStaffToToDoList(actionPlan.getId(), program_id);
            Navigation.findNavController(view).navigate(actionActionPlanStaffToToDoList);
        });
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_action_plan_title);
        }
    }
}