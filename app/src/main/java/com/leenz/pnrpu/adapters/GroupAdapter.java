package com.leenz.pnrpu.adapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.activities.MainActivity;
import com.leenz.pnrpu.models.Group;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private ViewGroup parent;
    private List<Group> groups;
    private SharedPreferences sharedPreferences;

    public GroupAdapter(List<Group> groups) {
        this.groups = groups;
    }

    public GroupAdapter(List<Group> groups, SharedPreferences sharedPreferences) {
        this.groups = groups;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_view,parent,false);
        view.setOnClickListener(v -> {
            if(sharedPreferences != null){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                TextView groupTV = v.findViewById(R.id.groupNameTV);
                String groupName = groupTV.getText().toString();
                editor.putString("groupName", groupName);
                editor.apply();
                MainActivity ctx = (MainActivity)parent.getContext();
                ctx.getGroupSearchLayout().setVisibility(View.GONE);
                ctx.setSelectedGroupName(groupName);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getGroupNameView().setText(groups.get(position).getGroup_name());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public void filterList(List<Group> filteredList) {
        groups = filteredList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView groupNameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            groupNameView = itemView.findViewById(R.id.groupNameTV);
        }

        public TextView getGroupNameView() {
            return groupNameView;
        }
    }
}
