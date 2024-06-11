package com.example.onlineincident;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewdepartments extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String [] place_name,dept_name,phone,email,description,value,dept_id;
    SharedPreferences sh;
    public static String did;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdepartments);

        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewdepartments.this;
        String q = "/Viewdepartments?login_id="+sh.getString("log_id","" );
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
                place_name = new String[ja1.length()];
                dept_name = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                description = new String[ja1.length()];
                dept_id = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    place_name[i] = ja1.getJSONObject(i).getString("place_name");
                    dept_name[i] = ja1.getJSONObject(i).getString("dept_name");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    description[i] = ja1.getJSONObject(i).getString("description");
                    dept_id[i] = ja1.getJSONObject(i).getString("dept_id");






                    value[i] = "place name:" + place_name[i] + "\n department name: " + dept_name[i] + "\n phone: " + phone[i] + "\n email: " + email[i] +"\n description:" +description[i] ;

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        did=dept_id[i];
        final CharSequence[] items = {"View Activity","Rate","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Viewdepartments.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("View Activity")) {

                    startActivity(new Intent(getApplicationContext(),Viewactivity.class));


                }
                else if (items[item].equals("Rate")) {
                    startActivity(new Intent(getApplicationContext(),Rate.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}