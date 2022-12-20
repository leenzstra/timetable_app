package com.leenz.pnrpu.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leenz.pnrpu.R;
import com.leenz.pnrpu.adapters.GroupAdapter;
import com.leenz.pnrpu.databinding.ActivityMainBinding;
import com.leenz.pnrpu.fragments.ProfessorFragment;
import com.leenz.pnrpu.fragments.TimetableFragment;
import com.leenz.pnrpu.models.timetablemodels.Group;
import com.leenz.pnrpu.utils.JSONReader;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<Group> groupList;
    View rootView;
    ActivityMainBinding activityMainBinding;
    SharedPreferences sharedPreferences;

    public void setSelectedGroupName(String selectedGroupName) {
        this.selectedGroupName = selectedGroupName;
        timetableFragment.setTimetableByGroupName(selectedGroupName);
    }

    public void setSelectedTimetableType(String selectedTimetableType){
        this.selectedTimetableType = selectedTimetableType;
        timetableFragment.setTimetableByTimetableType(selectedTimetableType);
    }

    String selectedTimetableType;
    String selectedGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        rootView = activityMainBinding.getRoot();
        setContentView(rootView);
        groupList = JSONReader.getGroupList();
        sharedPreferences = this.getSharedPreferences("currentGroup", Context.MODE_PRIVATE);

        timetableFragment = new TimetableFragment(sharedPreferences);
        professorFragment = new ProfessorFragment();
        replaceFragment(timetableFragment);
        activityMainBinding.bottomNavigation.setOnItemSelectedListener(item -> {

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

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    public void pushFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}