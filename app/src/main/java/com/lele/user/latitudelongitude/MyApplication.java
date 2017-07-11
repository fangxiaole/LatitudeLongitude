package com.lele.user.latitudelongitude;

import android.app.Application;

import com.lele.locationlibrary.LocationUtils;

/**
 * Created by lele on 2017/7/11.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocationUtils.getInstance().initLocationSDK(getApplicationContext());
    }
}
