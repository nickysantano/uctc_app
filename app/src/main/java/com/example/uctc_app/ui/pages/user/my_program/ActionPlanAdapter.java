package com.example.uctc_app.ui.pages.user.my_program;

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
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentDirections;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.pages.user.home.HomeUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.program_list.ProgramAdapter;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActionPlanAdapter extends RecyclerView.Adapter<ActionPlanAdapter.ViewHolder> {

    private Context context;
    private List<ActionPlan> actionList;

    public ActionPlanAdapter(Context context) {
        this.context = context;
    }

    public void setActionList(List<ActionPlan> actionList) {
        this.actionList = actionList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActionPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_action_plan_adapter, parent, false);
        return new ActionPlanAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ActionPlanAdapter.ViewHolder holder, int position) {
        ActionPlan actionPlan = actionList.get(position);
        Log.d("Hello", "" + actionPlan.getName());

        holder.name.setText(actionPlan.getName());

        holder.itemView.setOnClickListener(view -> {
//            ActionPlanFragmentDirections.ActionActionPlanToTaskUser actionActionPlanToTaskUser = ActionPlanFragmentDirections.actionActionPlanToTaskUser(actionPlan);
//            Navigation.findNavController(view).navigate(actionActionPlanToTaskUser);
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_action_plan_title);
        }
    }
}
