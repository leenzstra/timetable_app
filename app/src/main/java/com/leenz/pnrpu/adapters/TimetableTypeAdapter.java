package com.leenz.pnrpu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.activities.MainActivity;

public class TimetableTypeAdapter extends RecyclerView.Adapter<TimetableTypeAdapter.ViewHolder>{
    private ViewGroup parent;
    private String[] timetableTypes;

    public TimetableTypeAdapter(String[] timetableTypes) {
        this.timetableTypes = timetableTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetabletype_view,parent,false);
        view.setOnClickListener(v -> {
                TextView timetableTypeTV = v.findViewById(R.id.timetableTypeTV);
                String timetableType = timetableTypeTV.getText().toString();
                MainActivity ctx = (MainActivity)parent.getContext();
                ctx.setSelectedTimetableType(timetableType);
        });
        return new TimetableTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTimetableTypeView().setText(timetableTypes[position]);
    }

    @Override
    public int getItemCount() {
        return timetableTypes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView timetableTypeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timetableTypeView = itemView.findViewById(R.id.timetableTypeTV);
        }

        public TextView getTimetableTypeView() {
            return timetableTypeView;
        }
    }
}
