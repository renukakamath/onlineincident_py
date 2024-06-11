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

public class viewrules extends AppCompatActivity implements JsonResponse {
    ListView l1;
    String [] rule_id,title,description,value;
    SharedPreferences sh;
    public static String tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrules);

            l1=(ListView) findViewById(R.id.list);
    //        l1.setOnItemClickListener(this);
            sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            JsonReq JR = new JsonReq();
            JR.json_response = (JsonResponse) viewrules.this;
            String q = "/viewrules?login_id="+sh.getString("log_id","" );
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
                rule_id = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {

                    title[i] = ja1.getJSONObject(i).getString("title");
                    description[i] = ja1.getJSONObject(i).getString("description");
                    rule_id[i] = ja1.getJSONObject(i).getString("rule_id");






                    value[i] = "title:" + title[i] + "\n description : " + description[i] ;

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