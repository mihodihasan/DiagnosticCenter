package com.diagnostic.mhl.diagnosticcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientVH> {

    List<Patient> patientList;
    Context context;

    public PatientAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @NonNull
    @Override
    public PatientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PatientVH(LayoutInflater.from(context).inflate(R.layout.patient_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientVH holder, int position) {
        holder.textView.setText(patientList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    class PatientVH extends RecyclerView.ViewHolder{
        TextView textView;
        public PatientVH(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.patientTV);
        }
    }
}
