package com.example.uctc_app.ui.pages.staff.action_plan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramStaffFragmentDirections;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentDirections;

import java.util.List;

public class ActionPlanAdapter extends RecyclerView.Adapter<ActionPlanAdapter.ViewHolder> {

    private Context context;
    private List<ActionPlan> actionList;

    public ActionPlanAdapter(Context context) {
        this.context = context;
    }

    public void setActionList(List<ActionPlan> actionList) {
        this.actionList = actionList;
        Log.d("SETTING ActionList", getItemCount() + "actions");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_action_plan_adapter, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActionPlan actionPlan = actionList.get(position);
        Log.d("Hello", "" + actionPlan.getName());

        holder.itemView.setOnClickListener(view -> {
            ActionPlanStaffFragmentDirections.ActionActionPlanToToDoListStaff actionActionPlanToToDoListStaff = ActionPlanStaffFragmentDirections.actionActionPlanToToDoListStaff(actionPlan.getId());
            Navigation.findNavController(view).navigate(actionActionPlanToToDoListStaff);
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
