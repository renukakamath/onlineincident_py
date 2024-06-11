package com.example.onlineincident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewpubliccomplaints extends AppCompatActivity implements JsonResponse{
    ListView l1;
    String [] first_name,dept_name,complaint_type,title,description,reply,date_time,value,dept_id;
    SharedPreferences sh;
    public static String tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpubliccomplaints);

        l1=(ListView) findViewById(R.id.list);
//        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewpubliccomplaints.this;
        String q = "/Viewpubliccomplaints?login_id="+sh.getString("log_id","" );
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                first_name = new String[ja1.length()];
                dept_name = new String[ja1.length()];
                complaint_type = new String[ja1.length()];
                title = new String[ja1.length()];
                description = new String[ja1.length()];
                reply = new String[ja1.length()];
                date_time = new String[ja1.length()];

                dept_id = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    first_name[i] = ja1.getJSONObject(i).getString("first_name");
                    dept_name[i] = ja1.getJSONObject(i).getString("dept_name");

                    title[i] = ja1.getJSONObject(i).getString("title");
                    description[i] = ja1.getJSONObject(i).getString("description");
                    reply[i] = ja1.getJSONObject(i).getString("reply");
                    date_time[i] = ja1.getJSONObject(i).getString("date_time");
                    dept_id[i] = ja1.getJSONObject(i).getString("dept_id");






                    value[i] = "first name:" + first_name[i] + "\n department name: " + dept_name[i]+ "\n title: " + title[i] +"\n description:" +description[i] +"\n reply:"+reply[i] +"\ndate time:"+date_time[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}