package com.diagnostic.mhl.diagnosticcenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Diagnostic extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @BindView(R.id.toolbar)  Toolbar toolbar;
    @BindView(R.id.drawer_recyclerView)  RecyclerView drawerRecyclerView;
    @BindView(R.id.drawer_layout)  DrawerLayout drawerLayout;
    double total = 0, discount = 0, adv = 0, payable = 0, afterDisc = 0;

    String name, address, contact, ref;

    List<String> testNameList;
    List<Test> testList;
    List<Test> recyclerTestList;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MyRecyclerAdapter adapter;

    @BindView(R.id.name)
    EditText nameEt;
    @BindView(R.id.address)
    EditText addrEt;
    @BindView(R.id.contact)
    EditText contactEt;
    @BindView(R.id.ref)
    EditText refByEt;
    @BindView(R.id.total_bill)
    TextView totalBillTv;
    @BindView(R.id.total_after_disc)
    TextView totalAfterDiscTv;
    @BindView(R.id.payable)
    TextView payableTv;
    @BindView(R.id.discount_et)
    EditText discountEt;
    @BindView(R.id.advance_paid)
    EditText advancePaidEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        recyclerTestList = new ArrayList<>();
//        recyclerTestList.add(new Test(0,"Lushan",90));
        testList = new ArrayList<>();
        RealmResults<Test> tests = MyApplication.realm.where(Test.class).findAll();
        testNameList = new ArrayList<>();
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            testNameList.add(test.getTestName());
            testList.add(test);
        }
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerAdapter(recyclerTestList, this);
        recyclerView.setAdapter(adapter);
        totalBillTv.setText("0.0");
        addPrice(0);

        discountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    addPrice(0);
                } else {
                    discount = Double.parseDouble(s.toString());
                    addPrice(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        advancePaidEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().equals("")) {
                    addPrice(0);
                } else {
                    adv = Double.parseDouble(s.toString());
                    addPrice(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        DrawerAdapter drawerAdapter=new DrawerAdapter(testList);
        LinearLayoutManager drawerLayoutManager=new LinearLayoutManager(this);
        drawerRecyclerView.setLayoutManager(drawerLayoutManager);
        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setAdapter(drawerAdapter);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        drawerRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), drawerRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Intent intent = new Intent(getApplicationContext(), PatientListActivity.class);
                        intent.putExtra("position", testList.get(position).getTestName());
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void addPrice(double price) {
//        double total = Double.parseDouble(totalBillTv.getText().toString());
        total += price;
        totalBillTv.setText(String.valueOf(total));
        afterDisc = total - (total * discount) / 100;
        totalAfterDiscTv.setText(String.valueOf(afterDisc));
        payableTv.setText(String.valueOf(afterDisc - adv));
    }

    public void addTest(View view) {
        showInputDialog();
    }

    public void showInputDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Diagnostic.this);
        alertDialogBuilder.setTitle("");
        View view = LayoutInflater.from(Diagnostic.this).inflate(R.layout.dialog_test_add, null);
        final Spinner spinner = view.findViewById(R.id.test_list_spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, testNameList);
//set the spinners adapter to the previously created one.
        spinner.setAdapter(adapterSpinner);

        alertDialogBuilder
                .setView(view)
                .setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String testName = spinner.getSelectedItem().toString();
                Test testToAdd = null;
                for (Test test : testList) {
                    if (test.getTestName().equals(testName)) {
                        testToAdd = test;
                        addPrice(test.testPrice);
                        break;
                    }
                }
                recyclerTestList.add(testToAdd);
                adapter.notifyDataSetChanged();

            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void approve(View view) {
        String namePatient = nameEt.getText().toString();
        String addressPatient = addrEt.getText().toString();
        String contactPatient = contactEt.getText().toString();
        String refBy = refByEt.getText().toString();
        Patient patient = new Patient(namePatient, addressPatient, contactPatient, refBy, new RealmList<Test>(recyclerTestList.toArray(new Test[recyclerTestList.size()])));
        try {
            MyApplication.realm.beginTransaction();
            MyApplication.realm.copyToRealm(patient);
            MyApplication.realm.commitTransaction();
        } catch (Exception error) {
            MyApplication.realm.cancelTransaction();
        }
        nameEt.setText("");
        addrEt.setText("");
        contactEt.setText("");
        refByEt.setText("");
        discountEt.setText("");
        advancePaidEt.setText("");
        recyclerTestList.clear();
        adapter.notifyDataSetChanged();
        total=0;
        adv=0;
        discount=0;
        addPrice(0);
    }
}
