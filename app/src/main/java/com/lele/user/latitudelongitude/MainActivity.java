package com.lele.user.latitudelongitude;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.platform.comapi.location.CoordinateType;
import com.lele.locationlibrary.CoordinateTransformUtil;
import com.lele.locationlibrary.DistanceUtils;
import com.lele.locationlibrary.LocationUtils;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.functions.Action1;


public class MainActivity extends AppCompatActivity {
    private Context context;
    Button bt_get;
    TextView tx_location;
    Activity activity;
    int k,j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        activity = this;
        bt_get = (Button) findViewById(R.id.bt_get);
        tx_location = (TextView) findViewById(R.id.tx_location);
//        RxPermissions rxPermissions = new RxPermissions(this);
//        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
//                .subscribe(new Action1<Boolean>() {
//                    @TargetApi(Build.VERSION_CODES.M)
//                    @Override
//                    public void call(Boolean granted) {
//                        if (granted) { // 在android 6.0之前会默认返回true
//                            // 已经获取权限
//                            if (AndPermission.hasPermission(context, Manifest.permission.CAMERA)) {
//                                Log.e("leleTest", "确实有权限");
//                            } else {
//                                Log.e("leleTest", "拥有假权限");
//                            }
//                        } else {
//                            // 未获取权限
//                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
//                            Log.e("leleTest", "未授予权限");
//                        }
//                    }
//                });

//        AndPermission.with(this)
//                .requestCode(100)
//                .permission(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
//                .rationale(new RationaleListener() {
//                    @Override
//                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
//                        Log.e("leleTest","showRequestPermissionRationale");
//                        AlertDialog.newBuilder(context)
//                                .setTitle("友好提醒")
//                                .setMessage("你已拒绝过定位权限，沒有定位定位权限无法为你推荐附近的妹子，你看着办！")
//                                .setPositiveButton("好，给你",new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        rationale.resume();
//                                    }
//                                })
//                                .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        rationale.cancel();
//                                    }
//                                }).show();
//                    }
//                })
//                .callback(permissionListener)
//                .start();
        // 是否有不再提示并拒绝的权限。

        bt_get.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
//                Log.e("leleTest", "" + ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) + ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION));
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)||ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                        Log.e("leleTest", "refuse");
//                    } else {
//                        //已经拒绝权限，提示用户设置里面改
//                        Log.e("leleTest", "no refuse");
//                    }
//
//                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<100;i++){
                            LocationUtils.getInstance().getLocation(getApplicationContext(),new LocationUtils.GetLocationListener() {
                                @Override
                                public void location(final double latitude, final double longitude) {
                                    Log.e("leleTest", "latitude="+latitude+"longitude="+longitude);
                                    DeviceLocation currenLocation=new DeviceLocation(latitude,longitude);
                                    DeviceLocation beforeLocation=getLocation(context);
                                    if(beforeLocation!=null){
                                        final LatLng latLng1 = new LatLng(beforeLocation.getLatitude(),beforeLocation.getLongitude());
                                        final LatLng latLng2 = new LatLng(latitude,longitude);
                                        final double d=DistanceUtil.getDistance(latLng1,latLng2);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                tx_location.append("latitude="+latitude+"longitude="+longitude+"\n");
//                                                double[] c_location= CoordinateTransformUtil.gcj02towgs84(longitude,latitude);
//                                                tx_location.append("latitude="+c_location[0]+"longitude="+c_location[1]+"\n");
                                                tx_location.append(""+ d+"\n");
//                                                LatLng ios=new LatLng(22.646702,114.121059);
//                                                LatLng latLng3=new LatLng(c_location[1],c_location[0]);
//                                                double distence=DistanceUtil.getDistance(ios,latLng2);
//                                                double distence2=DistanceUtil.getDistance(ios,latLng3);
//                                                if(distence>distence2){
//                                                    k++;
//                                                }else{
//                                                    j++;
//                                                }
//                                                tx_location.append(k+"before-->"+distence+"\n");
//                                                tx_location.append(j+"after-->"+distence2+"\n");
                                            }
                                        });
                                        Log.e("leleTest", DistanceUtil.getDistance(latLng1,latLng2)+"");

                                    }
                                    putLocation(context,currenLocation);
                                }
                            });
                            try {
                                Thread.sleep(5*1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
//                formListenerGetLocation();
            }
        });
    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            Log.e("leleTest", "onSucceed" + AndPermission.hasPermission(context, grantPermissions));
//            if (AndPermission.hasAlwaysDeniedPermission(activity, grantPermissions)) {
//                // 第一种：用AndPermission默认的提示语。
//                AndPermission.defaultSettingDialog(activity, 400).show();

//                // 第二种：用自定义的提示语。
//                AndPermission.defaultSettingDialog(activity, 400)
//                        .setTitle("权限申请失败")
//                        .setMessage("您拒绝了我们必要的一些权限，已经没法愉快的玩耍了，请在设置中授权！")
//                        .setPositiveButton("好，去设置")
//                        .show();
//
//                // 第三种：自定义dialog样式。
//                SettingService settingService = AndPermission.defineSettingDialog(activity, 400);
//                // 你的dialog点击了确定调用：
//                settingService.execute();
//                // 你的dialog点击了取消调用：
//                settingService.cancel();
//            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

        }
    };

    class test implements Serializable {
        String name;
        int age;

        public test(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            test obj = (test) o;
            if (obj.name.equals(this.name)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return age;
        }
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 进入定位开关设置页
     *
     * @param context
     */
    public void toGPS(Context context) {
        Intent i = new Intent();
        i.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(i);
    }

    LocationManager locationManager;
    LocationListener locationListener;

    public void formListenerGetLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                //位置信息变化时触发

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //GPS状态变化时触发
            }

            @Override
            public void onProviderEnabled(String provider) {
                //GPS禁用时触发
            }

            @Override
            public void onProviderDisabled(String provider) {
                //GPS开启时触发
            }
        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length>1&&grantResults[0]== PackageManager.PERMISSION_GRANTED&&grantResults[1]== PackageManager.PERMISSION_GRANTED){
                    Log.e("leleTest","Permission success");
                }
                break;
        }
    }
    public static boolean isEqualStringArray(List<String>a ,List<String> b){
        if(a==null||b==null||a.size()!=b.size()){
            return false;
        }
        return a.containsAll(b);
    }
    /**
     * 存经纬度
     * @param context
     * @param location
     */
    public void putLocation(Context context,DeviceLocation location) {
        SharedPreferences sf = context.getSharedPreferences("lele",
                context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("location", location.getSpString());
        editor.apply();
    }

    /**
     * 获取经纬度
     * @param context
     * @return
     */
    public  DeviceLocation getLocation(Context context){
        SharedPreferences sf = context.getSharedPreferences("lele",
                context.MODE_PRIVATE);
        String str=sf.getString("location", "");
        DeviceLocation deviceLocation = null;
        if(!str.equals("")){
            deviceLocation=new DeviceLocation();
            String[] strs=str.split("\\|");
            try {
                deviceLocation.setLatitude(Double.parseDouble(strs[0]));
                deviceLocation.setLongitude(Double.parseDouble(strs[1]));
            }catch (NumberFormatException e){
                e.printStackTrace();
                return null;
            }
        }
        return deviceLocation;
    }
}
