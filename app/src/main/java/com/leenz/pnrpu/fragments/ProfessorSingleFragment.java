package com.leenz.pnrpu.fragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leenz.pnrpu.R;
import com.leenz.pnrpu.activities.MainActivity;
import com.leenz.pnrpu.adapters.CommentAdapter;
import com.leenz.pnrpu.adapters.ProfessorAdapter;
import com.leenz.pnrpu.models.timetablemodels.Professor;
import com.leenz.pnrpu.models.timetablemodels.ProfessorEvaluation;
import com.leenz.pnrpu.utils.ImageUtil;
import com.leenz.pnrpu.utils.JSONReader;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorSingleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorSingleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private Bitmap mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private Integer mParam5;

    public ProfessorSingleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfessorSingleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfessorSingleFragment newInstance(Bitmap param1, String param2, String param3, String param4, Integer param5) {
        ProfessorSingleFragment fragment = new ProfessorSingleFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putInt(ARG_PARAM5, param5);


        fragment.setArguments(args);
        return fragment;
    }
    FloatingActionButton openDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getInt(ARG_PARAM5);
        }

    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog((MainActivity) getActivity());
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.professor_dialog);


        //Initializing the views of the dialog.
        final EditText mark = dialog.findViewById(R.id.markEditText);
        final EditText comment = dialog.findViewById(R.id.commentEditText);
        Button submitButton = dialog.findViewById(R.id.submit_button);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_professor_single, container, false);
        layoutInflater = inflater;

        openDialog = rootView.findViewById(R.id.openDialogButton);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        ImageView myAwesomeImageView = (ImageView)rootView.findViewById(R.id.professorImageAvatar);

        BitmapDrawable ob = new BitmapDrawable(getResources(), mParam1);
        myAwesomeImageView.setImageBitmap(ImageUtil.getCroppedBitmap(mParam1));

        //myAwesomeImageView.setBackground(getCroppedBitmap(ob));



        TextView myAwesomeTextView = (TextView)rootView.findViewById(R.id.teacherInfoNameTV);
        myAwesomeTextView.setText(mParam2);

        myAwesomeTextView = (TextView)rootView.findViewById(R.id.teacherInfoPositionTV);
        myAwesomeTextView.setText(mParam3);

        myAwesomeTextView = (TextView)rootView.findViewById(R.id.teacherInfoDepartmentTV);
        myAwesomeTextView.setText(mParam4);

        try {
            generateObjects();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  rootView;
    }

    private LayoutInflater layoutInflater;
    private void generateObjects() throws JSONException, IOException {
        RecyclerView recyclerView = rootView.findViewById(R.id.commentsRecycleView);

        ProfessorEvaluation professorEval = JSONReader.getProfessorEvaluation(mParam5);

        recyclerView.setAdapter(new CommentAdapter(professorEval.getComments()));
        recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));

    }
}