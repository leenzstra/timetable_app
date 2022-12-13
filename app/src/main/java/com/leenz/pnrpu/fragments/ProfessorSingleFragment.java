package com.leenz.pnrpu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leenz.pnrpu.R;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

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
    public static ProfessorSingleFragment newInstance(String param1, String param2, String param3) {
        ProfessorSingleFragment fragment = new ProfessorSingleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_professor_single, container, false);
        TextView myAwesomeTextView = (TextView)rootView.findViewById(R.id.singleProfessorName);
        myAwesomeTextView.setText(mParam1);

        myAwesomeTextView = (TextView)rootView.findViewById(R.id.singleProfessorPosition);
        myAwesomeTextView.setText(mParam2);

        myAwesomeTextView = (TextView)rootView.findViewById(R.id.singleProfessorDepartment);
        myAwesomeTextView.setText(mParam3);
        return  rootView;
    }
}