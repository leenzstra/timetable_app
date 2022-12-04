package com.leenz.pnrpu.utils;

import android.content.Context;

import com.leenz.pnrpu.R;
import com.leenz.pnrpu.models.Day;
import com.leenz.pnrpu.models.Lesson;
import com.leenz.pnrpu.models.Timetable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONReader {
    public static Timetable readTimeTableJSONFile(Context context) throws IOException, JSONException {
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
        return new Timetable(days);
    }

    private static String readText(Context context, int resId) throws IOException{
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s;
        while((s = br.readLine()) != null){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
