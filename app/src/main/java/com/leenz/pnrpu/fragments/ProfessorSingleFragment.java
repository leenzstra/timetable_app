package com.leenz.pnrpu.fragments;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.utils.ImageUtil;

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

    // TODO: Rename and change types of parameters
    private Bitmap mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

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
    public static ProfessorSingleFragment newInstance(Bitmap param1, String param2, String param3, String param4) {
        ProfessorSingleFragment fragment = new ProfessorSingleFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);

        }
    }

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_professor_single, container, false);

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
        return  rootView;
    }
}