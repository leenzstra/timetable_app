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

public class ProfessorFragment extends Fragment {
    public ProfessorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private LayoutInflater layoutInflater;

    private void generateObjects() throws JSONException, IOException {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerProfessorpage);
        List<Professor> professors = JSONReader.getProfessorList("РИС-19-1б");
        recyclerView.setAdapter(new ProfessorAdapter(professors, this.getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));
    }

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_professor, container, false);
        layoutInflater = inflater;
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