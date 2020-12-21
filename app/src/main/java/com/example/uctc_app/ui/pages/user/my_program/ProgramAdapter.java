package com.example.uctc_app.ui.pages.user.my_program;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;

import java.util.List;


public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {


    private Context context;
    private List<Program> programList;

    public ProgramAdapter(Context context) {
        this.context = context;
    }

    public void setEventList(List<Program> programList) {
        this.programList = programList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_event_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Program program = programList.get(position);
        Log.d("Hello", ""+program.getName());

        holder.name.setText(program.getName());
        holder.description.setText(program.getDescription());
        holder.status.setText(program.getStatus());
        holder.creator.setText(program.getCreated_by());
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, description, status,creator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name_user);
            description = itemView.findViewById(R.id.lbl_program_category);
            status = itemView.findViewById(R.id.lbl_program_category_list);
            creator = itemView.findViewById(R.id.lbl_user_name);
        }
    }

}


