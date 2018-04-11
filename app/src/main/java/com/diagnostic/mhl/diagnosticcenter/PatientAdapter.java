package com.diagnostic.mhl.diagnosticcenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientVH> {

    List<Patient> patientList;
    Context context;
    String testName;

    public PatientAdapter(List<Patient> patientList, Context context, String id) {
        this.patientList = patientList;
        this.context = context;
        testName = id;
    }

    @NonNull
    @Override
    public PatientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PatientVH(LayoutInflater.from(context).inflate(R.layout.patient_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientVH holder, int position) {
        holder.textView.setText(patientList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    class PatientVH extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView deleteBtn;

        public PatientVH(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.patientTV);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    final String name=patientList.get(pos).getName();
                    MyApplication.realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<Patient> result = MyApplication.realm.where(Patient.class).equalTo("name", name).findAll();
                            result.deleteAllFromRealm();
                        }
                    });
                    patientList.remove(pos);
                    notifyDataSetChanged();

//                    Log.d("LSNLSNLSN", result.toString());
                }
            });
        }
    }
}
