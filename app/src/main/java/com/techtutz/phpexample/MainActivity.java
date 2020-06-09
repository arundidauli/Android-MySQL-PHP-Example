package com.techtutz.phpexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techtutz.phpexample.adapter.GetPatientAdapter;
import com.techtutz.phpexample.model.Patient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String TAG=MainActivity.class.getSimpleName();
    private Context context;
    private List<Patient> patientList;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        progressDialog=new ProgressDialog(this);
        patientList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_patient);

        findViewById(R.id.fab_add_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       fetch_patient();

    }

    private void setRecyclerViewPatient() {

        GetPatientAdapter patientAdapter = new GetPatientAdapter(context, patientList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(patientAdapter);


    }


    private void fetch_patient() {
        progressDialog.show();
        progressDialog.setMessage("Please wait...");
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.48/php/fetch.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        progressDialog.dismiss();
                        try {

                            JSONArray jsonArray = response.getJSONArray("patientlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                patientList.add(new Patient("",object.getString("patient_name"),object.getString("phone_num")));

                                //Log.e(TAG,object.getString("patient_name"));
                            }

                            setRecyclerViewPatient();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.i(TAG, error.getMessage());
                progressDialog.dismiss();

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);

    }


}