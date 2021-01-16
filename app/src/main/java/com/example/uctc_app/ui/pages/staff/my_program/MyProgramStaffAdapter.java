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
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.ui.pages.user.my_program.MyProgramUserAdapter;
import com.example.uctc_app.ui.pages.user.my_program.MyProgramUserFragmentDirections;

import java.util.List;

public class MyProgramStaffAdapter extends RecyclerView.Adapter<MyProgramStaffAdapter.ViewHolder> {


    private Context context;
    private List<Program> myProgramList;

    public MyProgramStaffAdapter(Context context) {
        this.context = context;
    }

    public void setEventList(List<Program> myProgramList) {
        this.myProgramList = myProgramList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyProgramStaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_program_staff_adapter, parent, false);
        return new MyProgramStaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProgramStaffAdapter.ViewHolder holder, int position) {
        Program program = myProgramList.get(position);
        Log.d("My Program List: ", "" + program.getName());

        holder.name.setText(program.getName());
        holder.date.setText(program.getDate());

        holder.itemView.setOnClickListener(view -> {
            MyProgramStaffFragmentDirections.ActionMyProgramStaffToActionPlan actionMyProgramStaffToActionPlan =
                    MyProgramStaffFragmentDirections.actionMyProgramStaffToActionPlan(program.getProgram_id() + "");
            Navigation.findNavController(view).navigate(actionMyProgramStaffToActionPlan);
        });
    }

    @Override
    public int getItemCount() {
        return myProgramList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name_my_program_staff);
            date = itemView.findViewById(R.id.lbl_date_my_program_staff);
        }
    }

}