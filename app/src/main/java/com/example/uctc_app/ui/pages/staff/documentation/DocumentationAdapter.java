package com.example.uctc_app.ui.pages.staff.documentation;

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
import com.example.uctc_app.model.local.role.Documentation;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.ui.pages.user.home.HomeUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.home.RecentEventAdapter;
import com.example.uctc_app.utils.Constants;

import java.util.List;

import butterknife.BindView;

public class DocumentationAdapter extends RecyclerView.Adapter<DocumentationAdapter.ViewHolder>{

    private Context context;
    private List<Documentation> documentationData;

    public DocumentationAdapter(Context context) {
        this.context = context;
    }

    public void setDocumentationData(List<Documentation> documentationData) {
        this.documentationData = documentationData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_documentation_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Documentation documentation = documentationData.get(position);
        Glide.with(context).load(Constants.BASE_IMAGE_DOCUMENTATION_URL + documentation.getPicPath()).into(holder.documentationAdapter);
    }

    @Override
    public int getItemCount() {
        return documentationData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView documentationAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            documentationAdapter = itemView.findViewById(R.id.lbl_documentation_img);
        }
    }

}