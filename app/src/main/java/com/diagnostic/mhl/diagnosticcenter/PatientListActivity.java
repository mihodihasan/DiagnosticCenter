package com.diagnostic.mhl.diagnosticcenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class PatientListActivity extends AppCompatActivity {

    @BindView(R.id.patientRecycler) RecyclerView recyclerView;
    LinearLayoutManager manager;
    PatientAdapter adapter;
    List<Patient> listPatients;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        String id=getIntent().getStringExtra("position");

        listPatients=new ArrayList<>();
        RealmResults<Patient> realmResults=MyApplication.realm.where(Patient.class).findAll();
        for (Patient patient:realmResults){
            List<Test> patientTests=patient.getPatientTestList();
            for (  Test test:patientTests){
                Log.d("LSNLSN", "onCreate: "+test);
                if (test.getTestName().equals(id)){
                    listPatients.add(patient);
                }
            }
        }

        manager=new LinearLayoutManager(this);
        adapter=new PatientAdapter(listPatients,this,id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }
}
