package com.techtutz.phpexample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.techtutz.phpexample.R;
import com.techtutz.phpexample.model.Patient;

import java.util.List;

public class GetPatientAdapter extends RecyclerView.Adapter<GetPatientAdapter.MyViewHolder>  {
    private Context context;
    private List<Patient> patientList;


    public GetPatientAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = patientList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.patient_name.setText(patientList.get(position).getPatient_name());
        holder.patient_contact.setText("Contact: "+patientList.get(position).getPatient_contact());




    }


    @Override
    public int getItemCount() {
        return  patientList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView  patient_name, patient_contact;


        MyViewHolder(View view) {
            super(view);
            patient_name=view.findViewById(R.id.patient_name);
            patient_contact=view.findViewById(R.id.patient_contact);



        }
    }

}
