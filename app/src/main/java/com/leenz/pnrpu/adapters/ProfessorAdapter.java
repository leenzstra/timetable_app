package com.leenz.pnrpu.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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
import com.leenz.pnrpu.models.Professor;
import com.leenz.pnrpu.utils.ImageReader;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ViewHolder> {

    private final List<Professor> professors;
    //private int mExpandedPosition=-1;
    private ViewGroup parent;
    private Context mContext;


    public ProfessorAdapter(List<Professor> professors, Context context) {
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
        final Professor item = professors.get(position);

//        String urlString = "https://sun3-8.userapi.com/impg/X_ws8dBN14g3D2xwjNI_q0_BjWxpf3mkUei_7g/mJUQjGQW3pI.jpg?size=1726x2160&quality=95&sign=433ed9d98241d6175eb30c9647e1be98&type=album";

        String urlString = professors.get(position).getImage();
        System.out.println(urlString);

        if (urlString.length() > 0) {
            AsyncTask<String, Void, Bitmap> t = new ImageReader.RequestTask().execute(urlString);

            try {
                professors.get(position).setImageBmp(t.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            holder.getImageView().setImageBitmap(professors.get(position).getImageBmp());
        }
        else{
            holder.getImageView().setImageResource(R.drawable.ic_baseline_professor_24);
        }

        holder.getNameView().setText(professors.get(position).getName());
        //holder.getDepartmentView().setText(professors.get(position).getDepartment());
        holder.getPositionView().setText(professors.get(position).getPosition());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fragmentJump(item);
            }
        });
    }
    private void fragmentJump(Professor mItemSelected) {
        ProfessorSingleFragment mFragment = ProfessorSingleFragment.newInstance(mItemSelected.getImageBmp(), mItemSelected.getName(), mItemSelected.getPosition(), mItemSelected.getDepartment());
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.pushFragment(mFragment);
    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView nameView;
        //private final TextView departmentView;
        private final TextView positionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.professorImageView);
            nameView = itemView.findViewById(R.id.professorName);
            //departmentView = itemView.findViewById(R.id.professorDepartment);
            positionView = itemView.findViewById(R.id.professorPosition);
        }
        public ImageView getImageView() {
            return imageView;
        }

        public TextView getNameView() {
            return nameView;
        }

        //public TextView getDepartmentView() {
//            return departmentView;
//        }
        public TextView getPositionView() {
            return positionView;
        }

    }
}
