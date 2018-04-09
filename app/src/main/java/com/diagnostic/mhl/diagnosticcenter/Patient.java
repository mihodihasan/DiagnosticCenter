package com.diagnostic.mhl.diagnosticcenter;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Patient extends RealmObject {
    @PrimaryKey
    int id;
    private String name, addr,contact,ref;
    private RealmList<Test> patientTestList;

    public Patient() {
        this.id = (int)System.currentTimeMillis();
    }

    public Patient(String name, String addr, String contact, String ref, RealmList<Test> patientTestList) {
        this.id = (int)System.currentTimeMillis();
        this.name = name;
        this.addr = addr;
        this.contact = contact;
        this.ref = ref;
        this.patientTestList = patientTestList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<Test> getPatientTestList() {
        return patientTestList;
    }

    public void setPatientTestList(RealmList<Test> patientTestList) {
        this.patientTestList = patientTestList;
    }
}
