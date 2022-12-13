package com.leenz.pnrpu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leenz.pnrpu.R;
import com.leenz.pnrpu.adapters.GroupAdapter;
import com.leenz.pnrpu.databinding.ActivityMainBinding;
import com.leenz.pnrpu.fragments.ProfessorFragment;
import com.leenz.pnrpu.fragments.TimetableFragment;
import com.leenz.pnrpu.models.Group;
import com.leenz.pnrpu.utils.JSONReader;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    SearchView groupSearchView;
    RecyclerView groupSearchRecyclerView;
    FloatingActionButton groupSearchButton;

    public ConstraintLayout getGroupSearchLayout() {
        return groupSearchLayout;
    }

    ConstraintLayout groupSearchLayout;
    List<Group> groupList;
    View rootView;
    GroupAdapter searchGroupAdapter;
    ActivityMainBinding activityMainBinding;
    SharedPreferences sharedPreferences;

    public String getSelectedGroupName() {
        return selectedGroupName;
    }

    public void setSelectedGroupName(String selectedGroupName) {
        this.selectedGroupName = selectedGroupName;
        timetableFragment.setTimetableByGroupName(selectedGroupName);
    }

    String selectedGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        rootView = activityMainBinding.getRoot();
        setContentView(rootView);
        timetableFragment = new TimetableFragment();
        professorFragment = new ProfessorFragment();
        replaceFragment(timetableFragment);
        groupList = JSONReader.getGroupList();
        sharedPreferences = this.getSharedPreferences("currentGroup", Context.MODE_PRIVATE);
        groupSearchLayout = rootView.findViewById(R.id.groupSearchLayout);
        groupSearchView = rootView.findViewById(R.id.groupSearchView);
        groupSearchRecyclerView = rootView.findViewById(R.id.groupSearchRecyclerView);
        groupSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        groupSearchButton = rootView.findViewById(R.id.groupChooseButton);
        groupSearchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


        searchGroupAdapter = new GroupAdapter(groupList, sharedPreferences);
        groupSearchRecyclerView.setAdapter(searchGroupAdapter);
        groupSearchButton.setOnClickListener(v -> {
            if(groupSearchLayout.getVisibility() == View.GONE){
                groupSearchLayout.setVisibility(View.VISIBLE);
            }else{
                groupSearchLayout.setVisibility(View.GONE);
            }
        });


        activityMainBinding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.timetable:
                    replaceFragment(timetableFragment);
                    break;
                case R.id.professor:
                    replaceFragment(professorFragment);
                    break;
            }
            return true;
        });


    }

    private TimetableFragment timetableFragment;
    private ProfessorFragment professorFragment;

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layout,fragment);
        fragmentTransaction.commit();
    }

    private void filter(String text) {
        List<Group> filteredList = new ArrayList<>();
        for (Group item : groupList) {
            if (item.getGroup_name().toLowerCase().startsWith(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        searchGroupAdapter.filterList(filteredList);
    }

}