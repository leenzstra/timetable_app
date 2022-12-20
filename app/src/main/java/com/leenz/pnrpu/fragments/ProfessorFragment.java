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
import com.leenz.pnrpu.utils.JSONReader;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfessorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfessorFragment extends Fragment {
    public ProfessorFragment() {
        // Required empty public constructor
    }

    public static ProfessorFragment newInstance() {
        return new ProfessorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private LayoutInflater layoutInflater;
    private void generateObjects() throws JSONException, IOException {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerProfessorpage);

//      RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.timetableRecyclerView);
        //Professor[] professors = new Professor[4];
//        professors[0] = new Professor(R.drawable.ic_baseline_professor_24,"АААААААА", "жопа1", "фффффф");
//        professors[1] = new Professor(R.drawable.ic_baseline_professor_24,"BBBBBBBB", "жопа2", "ыыыыыы");
//        professors[2] = new Professor(R.drawable.ic_baseline_professor_24,"CCCCCCCC", "жопа3", "яяяяяя");
//        professors[3] = new Professor(R.drawable.ic_baseline_professor_24,"DDDDDDDD", "жопа4", "чччччч");

        List<Professor> professors = JSONReader.getProfessorList("РИС-19-1б");

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
        try {
            generateObjects();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }
}