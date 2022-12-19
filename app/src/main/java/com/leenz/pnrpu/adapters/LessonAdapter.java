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
import com.leenz.pnrpu.models.timetablemodels.Lesson;

import java.util.Objects;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_timetablepage,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTimeView().setText(lessons[position].getTimeString());
        holder.getSubjectNameView().setText(lessons[position].getSubjectName());
        holder.getSubjectTypeView().setText(lessons[position].getSubjectType());
        holder.getLocationView().setText(lessons[position].getLocation());
        holder.getTeacherNameView().setText(lessons[position].getTeacherName());
        final boolean isLessonExists = !Objects.equals(lessons[position].getSubjectName(), "");
        holder.teacherAvatarCircle.setVisibility(isLessonExists ? View.VISIBLE : View.GONE);
        holder.subjectTypeView.setVisibility(isLessonExists ? View.VISIBLE : View.GONE);
//        holder.itemView.setActivated(isExpanded);
//        if(!holder.getSubjectNameView().getText().equals("")){
//            holder.itemView.setOnClickListener(v -> {
//                mExpandedPosition = isExpanded ? -1: position;
//                TransitionManager.beginDelayedTransition(parent);
//                notifyDataSetChanged();
//            });
//        }

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
        private final View teacherAvatarCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.timeButton);
            detailsView = itemView.findViewById(R.id.linearSubjectCard);
            subjectNameView = itemView.findViewById(R.id.lessonNameTV);
            subjectTypeView = itemView.findViewById(R.id.lessonTypeTV);
            teacherNameView = itemView.findViewById(R.id.lessonProfessorNameTV);
            locationView = itemView.findViewById(R.id.lessonLocationTV);
            teacherAvatarCircle = itemView.findViewById(R.id.teacherAvatarCircle);
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

        public View getAvatarView() {
            return teacherAvatarCircle;
        }
    }
}
