package com.example.uctc_app.ui.pages.user.home;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.pages.user.program_list.ProgramAdapter;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.Constants;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;

public class RecentEventAdapter extends RecyclerView.Adapter<RecentEventAdapter.ViewHolder> {

//    @BindView(R.id.imgProgram)
//    ImageView imgProgram;

    private Context context;
    private List<Program> programList;

    public RecentEventAdapter(Context context) {
        this.context = context;
    }

    public void setEventList(List<Program> programList) {
        this.programList = programList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecentEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recent_event_adapter, parent, false);
        return new RecentEventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentEventAdapter.ViewHolder holder, int position) {
        Program program = programList.get(position);
        Log.d("Hello", "" + program.getName());

//        Glide.with(context).load(Constants.BASE_IMAGE_PROGRAMS_URL + program.getThumbnail()).into(imgProgram);
        holder.title.setText(program.getName());
        holder.date.setText(program.getDate());

        holder.itemView.setOnClickListener(view -> {
            HomeUserFragmentDirections.ActionHomeUserToDetailProgramUser actionHomeUserToDetailProgramUser = HomeUserFragmentDirections.actionHomeUserToDetailProgramUser(program);
            Navigation.findNavController(view).navigate(actionHomeUserToDetailProgramUser);
        });
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, date;
        ImageView imgProgram;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lbl_recent_program_title);
            date = itemView.findViewById(R.id.lbl_recent_program_date);
            imgProgram = itemView.findViewById(R.id.imgProgram);
        }
    }


}