package com.diagnostic.mhl.diagnosticcenter;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Test extends RealmObject {
    @PrimaryKey
    private int id;
    private String testName;double testPrice;

    public Test() {
        id=0;
        testName="test";
        testPrice=0;
    }

    public Test(int id, String testName, double testPrice) {
        this.id = id;
        this.testName = testName;
        this.testPrice = testPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(double testPrice) {
        this.testPrice = testPrice;
    }
}
