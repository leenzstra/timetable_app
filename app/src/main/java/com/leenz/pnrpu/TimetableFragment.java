package com.leenz.pnrpu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
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
        currentDate = new Date();
        calendar.set(2022,9,1,0,0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        // JSON запрос
        //Пример. Чтение из файла TODO: заменить
        try {
            Timetable timetable = readTimeTableJSONFile(getContext());
            //Генерация интерфейса из класса
            generateObjects(timetable);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //
    }

    private void generateObjects(Timetable timetable) {

    }

    private Timetable readTimeTableJSONFile(Context context) throws IOException, JSONException{
        String jsonText = readText(context, R.raw.example_refactor);

        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray data = jsonRoot.getJSONArray("data");
        Day[] days = new Day[data.length()];

        for (int i = 0; i < data.length();i++){
            JSONObject obj = data.getJSONObject(i);
            JSONArray table = obj.getJSONArray("table");
            Lesson[] lessons = new Lesson[table.length()];
            for (int j = 0; j < table.length(); j++) {
                JSONObject subject = table.getJSONObject(j);
                String time = subject.getString("time");
                String subjectName = subject.getString("subject_name");
                String subjectType = subject.getString("subject_type");
                String teacher = subject.getString("teacher");
                String location = subject.getString("location");
                lessons[j] = new Lesson(time,subjectName,subjectType,teacher,location);
            }
            int id = obj.getInt("id");
            int groupId = obj.getInt("group_id");
            days[i] = new Day(id, groupId, obj.getString("day"), obj.getInt("week_num"),lessons);
        }
        Timetable result = new Timetable(days);
        return result;
    }

    private String readText(Context context, int resId) throws IOException{
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s = br.readLine()) != null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
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