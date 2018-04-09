package com.diagnostic.mhl.diagnosticcenter;

import android.app.Application;

import io.realm.Realm;

public class MyApplication extends Application {
    static Realm realm;
    @Override
    public void onCreate() {
        super.onCreate();

       if (realm==null){
           Realm.init(getApplicationContext());
           realm=Realm.getDefaultInstance();
       }
       Test test;//=new Test(1,"ACR",800);
        try {
            realm.beginTransaction();
            test = new Test(1,"ACR",800);
            realm.copyToRealm(test);
            test = new Test(2,"CCR",800);
            realm.copyToRealm(test);
            test = new Test(3,"C4",1000);
            realm.copyToRealm(test);
            test = new Test(4,"C3",900);
            realm.copyToRealm(test);
            realm.commitTransaction();
        } catch (Exception error) {
            realm.cancelTransaction();
        }
    }
}
