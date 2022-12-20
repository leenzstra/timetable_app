package com.leenz.pnrpu.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpStatus;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.StatusLine;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.ContentType;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.leenz.pnrpu.R;
import com.leenz.pnrpu.models.payloadmodels.SetMarkBody;
import com.leenz.pnrpu.models.timetablemodels.Comment;
import com.leenz.pnrpu.models.timetablemodels.Day;
import com.leenz.pnrpu.models.timetablemodels.Group;
import com.leenz.pnrpu.models.timetablemodels.Professor;
import com.leenz.pnrpu.models.timetablemodels.ProfessorEvaluation;
import com.leenz.pnrpu.models.timetablemodels.Response;
import com.leenz.pnrpu.models.timetablemodels.Timetable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.SneakyThrows;


public class JSONReader {

    public static String host = "http://192.168.1.3:3000";

    public static Timetable readTimeTableJSONFile(Context context) throws IOException, JSONException {
        String jsonText = readText(context, R.raw.example_refactor);

        JSONObject jsonRoot = new JSONObject(jsonText);
        JSONArray data = jsonRoot.getJSONArray("data");

        ObjectMapper objectMapper = new ObjectMapper();
        Day[] daysArray = objectMapper.readValue(data.toString(), Day[].class);

        return new Timetable(daysArray);

    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

    public static Timetable getTimetable(String group, String type) throws IOException, JSONException {
        URL baseUrl = new URL(host + "/timetable/timetables/");
        URL url = new URL(baseUrl, encodeValue(group) + "/" + encodeValue(type));

        JSONObject root = getRequest(url.toString());
        try {
            JSONArray data = root.getJSONArray("data");
            System.out.println(data);
            ObjectMapper objectMapper = new ObjectMapper();
            Day[] daysArray = objectMapper.readValue(data.toString(), Day[].class);
//            System.out.println(daysArray[0].toString());
            return new Timetable(daysArray);
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Group> getGroupList() {
        JSONObject root = getRequest(host + "/timetable/groups/");
        try {
            JSONArray data = root.getJSONArray("data");
            ObjectMapper objectMapper = new ObjectMapper();
            Group[] groupArray = objectMapper.readValue(data.toString(), Group[].class);
            return Arrays.stream(groupArray).collect(Collectors.toList());
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Professor> getProfessorList(String group) throws IOException {
        URL baseUrl = new URL(host + "/teachers/group/");
        URL url = new URL(baseUrl, encodeValue(group));

        JSONObject root = getRequest(url.toString());
        try {
            JSONArray data = root.getJSONArray("data");
            ObjectMapper objectMapper = new ObjectMapper();
            Professor[] professorArray = objectMapper.readValue(data.toString(), Professor[].class);
            return Arrays.stream(professorArray).collect(Collectors.toList());
        } catch (JSONException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ProfessorEvaluation getProfessorEvaluation(int teacher_id) throws IOException {
        URL baseUrl = new URL(host + "/teachers/eval/");
        URL url = new URL(baseUrl, Integer.toString(teacher_id));

        JSONObject root = getRequest(url.toString());
        //System.out.println(root.toString());
        try {
            JSONObject data = root.getJSONObject("data");
            JSONArray commentsJSONArr = data.getJSONArray("evaluations");
            ObjectMapper objectMapper = new ObjectMapper();
            Comment[] commentsArray = objectMapper.readValue(commentsJSONArr.toString(), Comment[].class);
            Double mark = data.getDouble("average_mark");
            int marksCount = data.getInt("count");
            ProfessorEvaluation p = ProfessorEvaluation.builder().averageMark(mark).markCount(marksCount).comments(commentsArray).build();

            //ProfessorEvaluation p = objectMapper.readValue(root.toString(), ProfessorEvaluation.class);
            return p;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response setMark(SetMarkBody payload) throws IOException {
        URL url = new URL(host + "/teachers/set_mark/");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(payload);

        JSONObject response = postRequest(url.toString(), json);
        try {
            return mapper.readValue(response.toString(), Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    private static JSONObject getRequest(String urlString) {
        AsyncTask<String, String, String> t = new GetRequestTask().execute(urlString);
        return new JSONObject(t.get());
    }

    @SneakyThrows
    private static JSONObject postRequest(String url, String payload) {
        AsyncTask<String, Void, String> t = new PostRequestTask().execute(url, payload);
        return new JSONObject(t.get());
    }

    public static class GetRequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }
    }

    public static class PostRequestTask extends AsyncTask<String, Void, String> {

        // params[0] - url, params[1] - json
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;

            try {
                HttpPost post = new HttpPost(params[0]);
                HttpEntity stringEntity = new StringEntity(params[1], ContentType.APPLICATION_JSON);
                post.setEntity(stringEntity);

                response = httpclient.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }

    }


}
