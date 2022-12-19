package com.leenz.pnrpu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.adapters.ProfessorAdapter;
import com.leenz.pnrpu.models.timetablemodels.Professor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public ProfessorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfessorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfessorFragment newInstance(String param1, String param2) {
        ProfessorFragment fragment = new ProfessorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    private LayoutInflater layoutInflater;
    private void generateObjects() {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerProfessorpage);

//      RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.timetableRecyclerView);
        Professor[] professors = new Professor[4];
        professors[0] = new Professor(R.drawable.ic_baseline_professor_24,"АААААААА", "жопа1", "фффффф");
        professors[1] = new Professor(R.drawable.ic_baseline_professor_24,"BBBBBBBB", "жопа2", "ыыыыыы");
        professors[2] = new Professor(R.drawable.ic_baseline_professor_24,"CCCCCCCC", "жопа3", "яяяяяя");
        professors[3] = new Professor(R.drawable.ic_baseline_professor_24,"DDDDDDDD", "жопа4", "чччччч");

        recyclerView.setAdapter(new ProfessorAdapter(professors, this.getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));

    }
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_professor, container, false);

        layoutInflater = inflater;
        // DatePicker


        // lessonViews
        generateObjects();

        return rootView;
    }
}