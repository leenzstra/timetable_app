package com.leenz.pnrpu.adapters;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.activities.MainActivity;
import com.leenz.pnrpu.fragments.ProfessorSingleFragment;
import com.leenz.pnrpu.models.timetablemodels.Professor;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ViewHolder> {

    private final Professor[] professors;
    //private int mExpandedPosition=-1;
    private ViewGroup parent;
    private Context mContext;


    public ProfessorAdapter(Professor[] professors, Context context) {
        this.professors = professors;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.professor_view,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Professor item = professors[position];

        holder.getImageView().setImageResource(professors[position].getImage());
        holder.getNameView().setText(professors[position].getName());
        holder.getDepartmentView().setText(professors[position].getDepartment());
        holder.getPositionView().setText(professors[position].getPosition());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragmentJump(item);
            }
        });
    }
    private void fragmentJump(Professor mItemSelected) {
        ProfessorSingleFragment mFragment = ProfessorSingleFragment.newInstance(mItemSelected.getName(), mItemSelected.getPosition(), mItemSelected.getDepartment());
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.pushFragment(mFragment);
    }

    @Override
    public int getItemCount() {
        return professors.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView nameView;
        private final TextView departmentView;
        private final TextView positionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.professorImageView);
            nameView = itemView.findViewById(R.id.professorName);
            departmentView = itemView.findViewById(R.id.professorDepartment);
            positionView = itemView.findViewById(R.id.professorPosition);
        }
        public ImageView getImageView() {
            return imageView;
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getDepartmentView() {
            return departmentView;
        }
        public TextView getPositionView() {
            return positionView;
        }

    }
}
