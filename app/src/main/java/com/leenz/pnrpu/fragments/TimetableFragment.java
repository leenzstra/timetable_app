package com.leenz.pnrpu.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.adapters.LessonAdapter;
import com.leenz.pnrpu.models.timetablemodels.Day;
import com.leenz.pnrpu.models.timetablemodels.Lesson;
import com.leenz.pnrpu.models.timetablemodels.Timetable;
import com.leenz.pnrpu.utils.JSONReader;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private Timetable timetable;
    private int currentWeekNumber;

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
        //TODO: изменить HardCode на автоматическое определение.
        currentDate = new Date();
        calendar.set(2022,9,1,0,0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        setTimetableByGroupName("АСУ-19-1б");
    }

    public void setTimetableByGroupName(String groupName){
        try {
            Timetable tmpTimetable = JSONReader.getTimetable(groupName, "Бакалавр (осенний, после смены)");

            if(tmpTimetable != null){
                timetable = tmpTimetable;
                generateObjects(timetable);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private LayoutInflater layoutInflater;
    private void generateObjects(Timetable timetable) {
        if(rootView == null) return;
        Day currDay = getCurrentDay(timetable);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerTimetablepage);
        if(currDay != null){
//            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.timetableRecyclerView);
//            Lesson[] lessons = sortLessons(currDay.getLessons());
            Lesson[] lessons = currDay.getLessons();
            recyclerView.setAdapter(new LessonAdapter(lessons));
            recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));

            TextView weekNumTV = rootView.findViewById(R.id.weekNumberTV);
            weekNumTV.setText(currDay.getWeekNum() + " неделя");
        }
        else{
            recyclerView.setAdapter(new LessonAdapter(new Lesson[0]));

            TextView weekNumTV = rootView.findViewById(R.id.weekNumberTV);
            weekNumTV.setText("неизвестно");
        }
    }


    private Day getCurrentDay(Timetable timetable){
        Day result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        for (Day curDay : timetable.getDays()) {
            if(curDay.getDayOfWeekNumber() == dayOfWeek
                    && curDay.getWeekNum() == currentWeekNumber){
                result = curDay;
                break;
            }
        }
        return result;
    }

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        layoutInflater = inflater;
        // DatePicker
        ImageButton prevButton = rootView.findViewById(R.id.btnArrowleft);
        ImageButton nextButton = rootView.findViewById(R.id.btnArrowright);
        prevButton.setOnClickListener(this::onPrevButtonClick);
        nextButton.setOnClickListener(this::onNextButtonClick);
        updateDateTextView();

        // lessonViews
        generateObjects(timetable);

        return rootView;
    }

    private void updateDateTextView(){
        TextView currentDateTV = rootView.findViewById(R.id.dateTV);
        @SuppressLint("WeekBasedYear") SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        String currentDateString = sdf.format(currentDate);

//        long diffInMilles = Math.abs(currentDate.getTime() - calendar.getTime().getTime());
        int septemberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        tempCalendar.setTime(currentDate);
        int currentWeekOfYear = tempCalendar.get(Calendar.WEEK_OF_YEAR);
        currentWeekNumber = 1 + Math.abs((currentWeekOfYear - septemberWeekOfYear))% 2;
        Day currentDay = getCurrentDay(timetable);
        String currentDayName = getString(R.string.default_day_string);
        if(currentDay != null) {
            currentDayName = currentDay.getDayName();
        }
        currentDateTV.setText(String.format("%s\n%s",currentDateString,currentDayName));
    }

    private Date currentDate;
    private final Calendar calendar = Calendar.getInstance();

    private void onNextButtonClick(View view) {
        setNextDay();
    }

    private void setNextDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 1);
        currentDate = cal.getTime();
        updateDateTextView();
        //TODO: Изменение расписания
        generateObjects(timetable);
    }

    public void onPrevButtonClick(View view) {
        setPreviousDay();
    }

    private void setPreviousDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, -1);
        currentDate = cal.getTime();
        updateDateTextView();

        //TODO: Изменение расписания
        generateObjects(timetable);
    }

    @Override
    public void onClick(View v) {

    }
}