package com.leenz.pnrpu.adapters;

import android.annotation.SuppressLint;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.models.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private final Lesson[] lessons;
    private int mExpandedPosition=-1;
    private ViewGroup parent;

    public LessonAdapter(Lesson[] lessons) {
        this.lessons = lessons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_view,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTimeView().setText(lessons[position].getTime());
        holder.getSubjectNameView().setText(lessons[position].getSubjectName());
        holder.getSubjectTypeView().setText(lessons[position].getSubjectType());
        holder.getLocationView().setText(lessons[position].getLocation());
        holder.getTeacherNameView().setText(lessons[position].getTeacherName());
        final boolean isExpanded = position == mExpandedPosition;
        holder.detailsView.setVisibility(isExpanded ? View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mExpandedPosition = isExpanded ? -1: position;
                TransitionManager.beginDelayedTransition(parent);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessons.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView timeView;
        private final LinearLayout detailsView;
        private final TextView subjectNameView;
        private final TextView subjectTypeView;
        private final TextView teacherNameView;
        private final TextView locationView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.timeTV);
            detailsView = itemView.findViewById(R.id.details_linear_layout);
            subjectNameView = itemView.findViewById(R.id.subjectNameTV);
            subjectTypeView = itemView.findViewById(R.id.subjectTypeTV);
            teacherNameView = itemView.findViewById(R.id.teacherTV);
            locationView = itemView.findViewById(R.id.locationTV);
        }


        public TextView getTimeView() {
            return timeView;
        }

        public TextView getSubjectNameView() {
            return subjectNameView;
        }

        public TextView getSubjectTypeView() {
            return subjectTypeView;
        }

        public TextView getTeacherNameView() {
            return teacherNameView;
        }

        public TextView getLocationView() {
            return locationView;
        }

        public LinearLayout getDetailsView() { return detailsView; }
    }
}
