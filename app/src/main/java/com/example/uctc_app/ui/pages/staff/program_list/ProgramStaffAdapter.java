package com.example.uctc_app.ui.pages.staff.program_list;

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
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProgramStaffAdapter  extends RecyclerView.Adapter<ProgramStaffAdapter.ViewHolder> {


    private Context context;
    private List<Program> programList;

    public ProgramStaffAdapter(Context context) {
        this.context = context;
    }

    public void setEventList(List<Program> programList) {
        this.programList = programList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProgramStaffAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_program_staff_adapter, parent, false);
        return new ProgramStaffAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramStaffAdapter.ViewHolder holder, int position) {
        Program program = programList.get(position);
        Log.d("Hello", "" + program.getName());

        holder.name.setText(program.getName());
        holder.description.setText(program.getDescription());
        holder.status.setText(program.getStatus());
        holder.creator.setText(program.getCreated_by());

        holder.itemView.setOnClickListener(view -> {
            ProgramStaffFragmentDirections.ActionProgramStaffToDetailProgram actionProgramStaffToDetailProgram = ProgramStaffFragmentDirections.actionProgramStaffToDetailProgram(program);
            Navigation.findNavController(view).navigate(actionProgramStaffToDetailProgram);
        });

        holder.delete.setOnClickListener(v -> {
            ProgramRepository repository = ProgramRepository.getInstance(SharedPreferenceHelper.getInstance(context).getAccessToken());
            repository.deleteProgram(program.getProgram_id());

            ProgramStaffFragmentDirections.ActionNavProgramStaffSelf actionNavProgramStaffSelf = ProgramStaffFragmentDirections.actionNavProgramStaffSelf();
            Navigation.findNavController(v).navigate(actionNavProgramStaffSelf);
        });
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, description, status,creator;
        private FloatingActionButton delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name_user_list_admin);
            description = itemView.findViewById(R.id.lbl_email_user_list_admin);
            status = itemView.findViewById(R.id.lbl_txt_status_event);
            creator = itemView.findViewById(R.id.lbl_user_name);
            delete = itemView.findViewById(R.id.lbl_program_del);
        }
    }
}