package com.leenz.pnrpu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimetableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimetableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimetableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimetableFragment newInstance(String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        currentDate = new Date();
        calendar.set(2022,9,1,0,0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
    }
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_timetable, container, false);

        Button prevButton = (Button) rootView.findViewById(R.id.previousDayButton);
        Button nextButton = (Button) rootView.findViewById(R.id.nextDayButton);
        prevButton.setOnClickListener(this::onPrevButtonClick);
        nextButton.setOnClickListener(this::onNextButtonClick);
        updateDateTextView();
        // Inflate the layout for this fragment
        return rootView;

    }

    private void updateDateTextView(){
        TextView currentDateTV = (TextView) rootView.findViewById(R.id.monthYearTV);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YY", Locale.getDefault());
        String currentDateString = sdf.format(currentDate);

//        long diffInMilles = Math.abs(currentDate.getTime() - calendar.getTime().getTime());
        int septemberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        tempCalendar.setTime(currentDate);
        int currentWeekOfYear = tempCalendar.get(Calendar.WEEK_OF_YEAR);
        int weekNumber = 1 + Math.abs((currentWeekOfYear - septemberWeekOfYear))% 2;
        currentDateTV.setText(currentDateString + "\n" + "Номер недели: " + (weekNumber));
    }

    private Date currentDate;
    private Calendar calendar = Calendar.getInstance();

    private void onNextButtonClick(View view) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 1);
        currentDate = cal.getTime();
        updateDateTextView();

        //TODO: Изменение расписания
    }

    public void onPrevButtonClick(View view) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, -1);
        currentDate = cal.getTime();
        updateDateTextView();

        //TODO: Изменение расписания
    }

    @Override
    public void onClick(View v) {

    }
}