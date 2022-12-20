package com.leenz.pnrpu.adapters;

import static android.app.PendingIntent.getActivity;

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
import com.leenz.pnrpu.models.timetablemodels.Professor;
import com.leenz.pnrpu.utils.ImageReader;
import com.leenz.pnrpu.utils.ImageUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ViewHolder> {

    private final List<Professor> professors;
    private ViewGroup parent;
    private final Context mContext;


    public ProfessorAdapter(List<Professor> professors, Context context) {
        this.professors = professors;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_professorpage,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Professor item = professors.get(position);

        String urlString = professors.get(position).getImage();
        System.out.println(urlString);

        if (urlString.length() > 0) {
            AsyncTask<String, Void, Bitmap> t = new ImageReader.RequestTask().execute(urlString);

            try {
                professors.get(position).setImageBmp(t.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            if (professors.get(position).getImageBmp() != null) {
                holder.getImageView().setImageBitmap(ImageUtil.getCroppedBitmap(professors.get(position).getImageBmp()));
            }
        }
        holder.getNameView().setText(professors.get(position).getName());
        holder.getPositionView().setText(professors.get(position).getPosition());

        holder.itemView.setOnClickListener(v -> fragmentJump(item));
    }
    private void fragmentJump(Professor mItemSelected) {
        ProfessorSingleFragment mFragment = ProfessorSingleFragment.newInstance(mItemSelected.getImageBmp(), mItemSelected.getName(), mItemSelected.getPosition(), mItemSelected.getDepartment(), mItemSelected.getId());

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
        private final TextView positionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.professorImage);
            nameView = itemView.findViewById(R.id.professorNameTV);
            positionView = itemView.findViewById(R.id.professorPositionTV);
        }
        public ImageView getImageView() {
            return imageView;
        }

        public TextView getNameView() {
            return nameView;
        }

        public TextView getPositionView() {
            return positionView;
        }
    }
}
