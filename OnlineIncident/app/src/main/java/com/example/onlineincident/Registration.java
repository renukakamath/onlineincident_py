package com.example.onlineincident;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Registration extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    String fname,lname,address,phone,email,username,password;
    String[] place_id,place_name,value;

public static String pid;
    Spinner s1;

    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
s1=(Spinner)findViewById(R.id.spinner);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.address);
        e4=(EditText)findViewById(R.id.phone);
        e5=(EditText) findViewById(R.id.email);
        e6=(EditText) findViewById(R.id.uname);
        e7=(EditText) findViewById(R.id.password);


        b1=(Button) findViewById(R.id.registration);


        s1.setOnItemSelectedListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Registration.this;
        String q = "/viewproductspinner";
        q = q.replace(" ", "%20");
        JR.execute(q);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();

                address=e3.getText().toString();

                phone=e4.getText().toString();
                email=e5.getText().toString();
                username=e6.getText().toString();
                password=e7.getText().toString();

                if(fname.equalsIgnoreCase("")|| !fname.matches("[a-zA-Z ]+"))
                {
                    e1.setError("Enter your firstname");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase("")|| !lname.matches("[a-zA-Z ]+"))
                {
                    e2.setError("Enter your lastname");
                    e2.setFocusable(true);
                }

                else if(address.equalsIgnoreCase("")|| !address.matches("[a-zA-Z ]+"))
                {
                    e3.setError("Enter your place");
                    e3.setFocusable(true);
                }

                else if(phone.equalsIgnoreCase("")|| phone.length()!=10)
                {
                    e4.setError("Enter your phone");
                    e4.setFocusable(true);
                }
                else if(email.equalsIgnoreCase("")|| !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"))
                {
                    e5.setError("Enter your email");
                    e5.setFocusable(true);
                }

                else if(username.equalsIgnoreCase(""))
                {
                    e6.setError("Enter your username");
                    e6.setFocusable(true);
                }
                else if(password.equalsIgnoreCase(""))
                {
                    e7.setError("Enter your password");
                    e7.setFocusable(true);
                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Registration.this;
                    String q = "/Registration?fname=" + fname + "&lname=" + lname + "&address=" + address + "&phone=" + phone + "&email=" + email + "&username=" + username + "&password=" + password + "&pid="+pid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Registration")) {

                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));

                } else if (status.equalsIgnoreCase("duplicate")) {
                    startActivity(new Intent(getApplicationContext(), Registration.class));
                    Toast.makeText(getApplicationContext(), "Username and Password already Exist...", Toast.LENGTH_LONG).show();

                }else if (status.equalsIgnoreCase("already")) {
                    Toast.makeText(getApplicationContext(), "Username Or Password ALREADY EXIST", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Registration.class));

                }else {
                    startActivity(new Intent(getApplicationContext(), Registration.class));

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("viewproductspinner"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    place_name=new String[ja1.length()];

                    place_id=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        place_name[i]=ja1.getJSONObject(i).getString("place_name");
                        place_id[i]=ja1.getJSONObject(i).getString("place_id");





                        value[i]=place_name[i];


                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    s1.setAdapter(ar);
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
        pid=place_id[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}