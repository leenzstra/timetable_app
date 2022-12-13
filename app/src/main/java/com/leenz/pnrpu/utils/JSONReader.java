package com.leenz.pnrpu.utils;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leenz.pnrpu.R;
import com.leenz.pnrpu.models.Day;
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

        ObjectMapper objectMapper = new ObjectMapper();
        Day[] daysArray = objectMapper.readValue(data.toString(), Day[].class);

        return new Timetable(daysArray);

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
