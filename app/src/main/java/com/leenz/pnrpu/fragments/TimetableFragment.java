package com.leenz.pnrpu.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.adapters.GroupAdapter;
import com.leenz.pnrpu.adapters.LessonAdapter;
import com.leenz.pnrpu.adapters.TimetableTypeAdapter;
import com.leenz.pnrpu.models.timetablemodels.Day;
import com.leenz.pnrpu.models.timetablemodels.Group;
import com.leenz.pnrpu.models.timetablemodels.Lesson;
import com.leenz.pnrpu.models.timetablemodels.Timetable;
import com.leenz.pnrpu.utils.JSONReader;

import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lombok.Getter;

public class TimetableFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final SharedPreferences sharedPreferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Timetable timetable;
    private int currentWeekNumber;
    private TextView selectedGroupTV;
    private TextView selectedTimetableTypeTV;

    public TimetableFragment() {
        // Required empty public constructor
        sharedPreferences = null;
    }

    public TimetableFragment(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

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
        calendar.set(2022, 9, 1, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        setTimetableByGroupNameAndTimetableType("АСУ-19-1б", "Бакалавр (осенний, после смены)");
    }

    public void setTimetableByGroupName(String groupName) {
        setTimetableByGroupNameAndTimetableType(groupName, selectedTimetableType);
    }

    public void setTimetableByTimetableType(String timetableByTimetableType) {
        setTimetableByGroupNameAndTimetableType(selectedGroupName, timetableByTimetableType);
    }

    public void setTimetableByGroupNameAndTimetableType(String groupName, String timetableType) {
        try {
            Timetable tmpTimetable = JSONReader.getTimetable(groupName, timetableType);

            if (tmpTimetable != null) {
                timetable = tmpTimetable;
                generateObjects(timetable);
                if (groupName != null)
                    selectedGroupName = groupName;
                if (timetableType != null)
                    selectedTimetableType = timetableType;
                updateGroupNameAndTimetableTextViews();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Getter
    private String selectedGroupName;
    @Getter
    private String selectedTimetableType;

    private void updateGroupNameAndTimetableTextViews() {
        if (selectedGroupTV != null)
            selectedGroupTV.setText(selectedGroupName);
        if (selectedTimetableTypeTV != null)
            selectedTimetableTypeTV.setText(selectedTimetableType);
    }

    private LayoutInflater layoutInflater;

    private void generateObjects(Timetable timetable) {
        if (rootView == null) return;
        Day currDay = getCurrentDay(timetable);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerTimetablepage);
        if (currDay != null) {
            Lesson[] lessons = currDay.getLessons();
            recyclerView.setAdapter(new LessonAdapter(lessons));
            recyclerView.setLayoutManager(new LinearLayoutManager(layoutInflater.getContext()));

            TextView weekNumTV = rootView.findViewById(R.id.weekNumberTV);
            weekNumTV.setText(currDay.getWeekNum() + " неделя");
        } else {
            recyclerView.setAdapter(new LessonAdapter(new Lesson[0]));

            TextView weekNumTV = rootView.findViewById(R.id.weekNumberTV);
            weekNumTV.setText("неизвестно");
        }
    }


    private Day getCurrentDay(Timetable timetable) {
        Day result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        for (Day curDay : timetable.getDays()) {
            if (curDay.getDayOfWeekNumber() == dayOfWeek
                    && curDay.getWeekNum() == currentWeekNumber) {
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
        selectedGroupTV = rootView.findViewById(R.id.groupNameTV);
        selectedGroupTV.setOnClickListener(selectGroupTVClickListener);
        selectedTimetableTypeTV = rootView.findViewById(R.id.timetableTypeTV);
        selectedTimetableTypeTV.setOnClickListener(selectTimetableTypeTVClickListener);
        ImageButton prevButton = rootView.findViewById(R.id.btnArrowleft);
        ImageButton nextButton = rootView.findViewById(R.id.btnArrowright);
        prevButton.setOnClickListener(this::onPrevButtonClick);
        nextButton.setOnClickListener(this::onNextButtonClick);
        updateDateTextView();

        updateGroupNameAndTimetableTextViews();
        // lessonViews
        generateObjects(timetable);

        return rootView;
    }

    private final View.OnClickListener selectGroupTVClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View popupView = layoutInflater.inflate(R.layout.group_select_menu, null);
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true;
            List<Group> groupList = JSONReader.getGroupList();
            GroupAdapter searchGroupAdapter = new GroupAdapter(groupList, sharedPreferences);
            RecyclerView rv = popupView.findViewById(R.id.groupSelectRecyclerView);
            SearchView sv = popupView.findViewById(R.id.groupSelectSearchView);

            rv.setAdapter(searchGroupAdapter);
            PopupWindow pw = new PopupWindow(popupView, width, height, focusable);
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    pw.dismiss();
                    return true;
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

                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    return false;
                }
            });

            pw.showAsDropDown(v, 0, 0, Gravity.CENTER_HORIZONTAL);
            popupView.setOnTouchListener((v1, event) -> {
                pw.dismiss();
                return true;
            });
        }
    };

    private final View.OnClickListener selectTimetableTypeTVClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View popupView = layoutInflater.inflate(R.layout.timetabletype_select_menu, null);
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true;
            try {
                String[] timetableTypes = JSONReader.getTimetableTypes(selectedGroupName);
                RecyclerView rv = popupView.findViewById(R.id.timetableTypeSelectRecyclerView);
                TimetableTypeAdapter adapter = new TimetableTypeAdapter(timetableTypes);
                rv.setAdapter(adapter);

                PopupWindow pw = new PopupWindow(popupView, width, height, focusable);
                pw.showAsDropDown(v, 0, 0, Gravity.CENTER_HORIZONTAL);
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        pw.dismiss();
                        return true;
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    };

    private void updateDateTextView() {
        TextView currentDateTV = rootView.findViewById(R.id.dateTV);
        TextView todayTV = rootView.findViewById(R.id.todayStringTV);
        @SuppressLint("WeekBasedYear") SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        String currentDateString = sdf.format(currentDate);

        int septemberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        tempCalendar.setTime(currentDate);
        int currentWeekOfYear = tempCalendar.get(Calendar.WEEK_OF_YEAR);
        currentWeekNumber = 1 + Math.abs((currentWeekOfYear - septemberWeekOfYear)) % 2;
        Day currentDay = getCurrentDay(timetable);
        String currentDayName = getString(R.string.default_day_string);
        if (currentDay != null) {
            currentDayName = currentDay.getDayName();
        }
        currentDateTV.setText(currentDateString);
        todayTV.setText(currentDayName);
    }

    private Date currentDate;
    private final Calendar calendar = Calendar.getInstance();

    private void onNextButtonClick(View view) {
        setNextDay();
    }

    private void setNextDay() {
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

    private void setPreviousDay() {
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