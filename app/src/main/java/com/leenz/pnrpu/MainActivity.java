package com.leenz.pnrpu;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leenz.pnrpu.databinding.ActivityMainBinding;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        timetableFragment = new TimetableFragment();
        professorFragment = new ProfessorFragment();
        replaceFragment(timetableFragment);
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

}