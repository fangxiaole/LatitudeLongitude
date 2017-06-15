package com.lele.user.latitudelongitude;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lele.locationlibrary.LocationUtils;

public class MainActivity extends AppCompatActivity {
    private Context context;
    Button bt_get;
    TextView tx_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        bt_get=(Button)findViewById(R.id.bt_get);
        tx_location=(TextView)findViewById(R.id.tx_location);
        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    if(!isOPen(context)){
//                        toGPS(context);
//                        return;
//                    }
//                    LocationUtils.getInstance().getLocation(context, new LocationUtils.GetLocationListener() {
//                        @Override
//                        public void updateLocation(double latitude, double longitude) {
//                            tx_location.append("latitude="+latitude+"longitude="+longitude+"\n");
//                        }
//                    });
//                    LatLong start = new LatLong(114.035641, 22.615255);
//                    LatLong end = new LatLong(114.126047,22.608449);
//                   tx_location.append(DistanceUtils.getDistance(start, end)+"\n");
                LocationUtils.getInstance().initLocationSDK(getApplicationContext());
                LocationUtils.getInstance().getLocation(getApplicationContext(), new LocationUtils.GetLocationListener() {
                    @Override
                    public void location(double latitude, double longitude) {
                        Log.e("leleTest","latitude="+latitude+"longitude="+longitude+"\n");
                    }
                });
                }
        });
    }
    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public  boolean isOPen(final Context context) {
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
    public  void toGPS(Context context) {
        Intent i = new Intent();
        i.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(i);
    }
}
