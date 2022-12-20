package com.leenz.pnrpu.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.models.timetablemodels.Comment;

import java.util.Objects;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private final Comment[] comments;
    private ViewGroup parent;

    public CommentAdapter(Comment[] comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment,parent,false);
        return new CommentAdapter.ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        holder.getMarkTextView().setText(Integer.toString(comments[position].getMark()));
        holder.getCommentSection().setText(comments[position].getComment());
    }

    @Override
    public int getItemCount() {
        return comments.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView commentSection;
        private final TextView markTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentSection = itemView.findViewById(R.id.commentSection);
            markTextView = itemView.findViewById(R.id.markTV);
        }


        public TextView getCommentSection() {
            return commentSection;
        }

        public TextView getMarkTextView() {
            return markTextView;
        }

    }
}
