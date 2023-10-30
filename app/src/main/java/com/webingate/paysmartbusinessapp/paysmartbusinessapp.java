package com.webingate.paysmartbusinessapp;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * Created by Panacea-Soft on 7/10/18.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class paysmartbusinessapp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
    }
}
