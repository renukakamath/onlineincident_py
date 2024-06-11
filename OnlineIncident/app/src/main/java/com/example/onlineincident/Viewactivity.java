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

public class Viewactivity extends AppCompatActivity implements JsonResponse {
    ListView l1;
    String [] activity_id,title,description,activity_date,value;
    SharedPreferences sh;
    public static String tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewactivity);

        l1=(ListView) findViewById(R.id.list);
        //        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewactivity.this;
        String q = "/Viewactivity?login_id="+sh.getString("log_id","" )+"&did="+Viewdepartments.did;
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

                title = new String[ja1.length()];
                description = new String[ja1.length()];
                activity_id = new String[ja1.length()];
                activity_date = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    title[i] = ja1.getJSONObject(i).getString("title");
                    description[i] = ja1.getJSONObject(i).getString("description");
                    activity_id[i] = ja1.getJSONObject(i).getString("activity_id");
                    activity_date[i] = ja1.getJSONObject(i).getString("activity_date");






                    value[i] = "title:" + title[i] + "\n description : " + description[i] + "\n activity date : " + activity_date[i] ;

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