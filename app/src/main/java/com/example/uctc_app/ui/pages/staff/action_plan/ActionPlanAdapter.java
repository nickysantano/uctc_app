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
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentArgs;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentDirections;

import java.util.List;

public class ActionPlanAdapter extends RecyclerView.Adapter<ActionPlanAdapter.ViewHolder> {

    private Context context;
    private List<ActionPlan> actionList;
    private String program_id;

    public ActionPlanAdapter(Context context) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_action_plan_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActionPlan actionPlan = actionList.get(position);
        Log.d("Hello", "" + actionPlan.getName());

        holder.name.setText(actionPlan.getName());
        holder.description.setText(actionPlan.getDescription());

        holder.itemView.setOnClickListener(view -> {
            ActionPlanStaffFragmentDirections.ActionActionPlanToToDoListStaff actionActionPlanToToDoListStaff =
                    ActionPlanStaffFragmentDirections.actionActionPlanToToDoListStaff(actionPlan.getId(), program_id);
            Navigation.findNavController(view).navigate(actionActionPlanToToDoListStaff);

//            viewModel.getActionPlans( Integer.parseInt(ActionPlanFragmentArgs.fromBundle(getArguments()).getProgramId())).observe(requireActivity(), observeViewModel); //untuk ambil argument navigation
        });
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_action_plan_title);
            description = itemView.findViewById(R.id.lbl_action_plan_description);
        }
    }

}
