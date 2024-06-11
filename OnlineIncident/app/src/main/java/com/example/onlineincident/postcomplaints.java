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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class postcomplaints extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    EditText e1,e2;
    Button b1;
    ListView l1;
    String complaint,title;
      String[] dept_name,dept_id,value,complaints,reply,date,ti,comp_id;

    public static String did,cid;
    Spinner s1;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postcomplaints);
        s1=(Spinner)findViewById(R.id.spinner);
        e1=(EditText) findViewById(R.id.comp);
l1=(ListView) findViewById(R.id.list);
l1.setOnItemClickListener(this);
        e2=(EditText) findViewById(R.id.tit);
        b1=(Button) findViewById(R.id.complaint);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        s1.setOnItemSelectedListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) postcomplaints.this;
        String q = "/viewspinner";
        q = q.replace(" ", "%20");
        JR.execute(q);


        JsonReq JR1 = new JsonReq();
        JR1.json_response = (JsonResponse) postcomplaints.this;
        String q1 = "/viewcomplaints";
        q1 = q1.replace(" ", "%20");
        JR1.execute(q1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint=e1.getText().toString();
                title=e2.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) postcomplaints.this;
                String q = "/Complaint?compliant=" + complaint +"&login_id=" + sh.getString("log_id", "") +"&did="+did +"&tit="+title;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Complaint")) {

                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), " SUCCESS", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Userhome.class));

                }

            }
            else if(method.equalsIgnoreCase("viewspinner"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    dept_name=new String[ja1.length()];

                    dept_id=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        dept_name[i]=ja1.getJSONObject(i).getString("dept_name");
                        dept_id[i]=ja1.getJSONObject(i).getString("dept_id");





                        value[i]=dept_name[i];


                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    s1.setAdapter(ar);
                }

            }

            else if(method.equalsIgnoreCase("viewcomplaints")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    complaints = new String[ja1.length()];
                    comp_id = new String[ja1.length()];
                    reply = new String[ja1.length()];
                    date = new String[ja1.length()];
                    value = new String[ja1.length()];
                    ti = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        complaints[i] = ja1.getJSONObject(i).getString("complaint");
                        comp_id[i] = ja1.getJSONObject(i).getString("complaint_id");
                        reply[i] = ja1.getJSONObject(i).getString("reply");
                        date[i] = ja1.getJSONObject(i).getString("date_time");
                        ti[i] = ja1.getJSONObject(i).getString("title");
                        value[i] = "complaint: " + complaints[i] + "\nreply: " + reply[i] + "\ndate: " + date[i] + "\n title:" + ti[i];

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);
                    l1.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        did=dept_id[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        cid=comp_id[i];
        final CharSequence[] items = {"Upload Image","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(postcomplaints.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Upload Image")) {

                    startActivity(new Intent(getApplicationContext(),Uploadimage.class));


                }

                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}