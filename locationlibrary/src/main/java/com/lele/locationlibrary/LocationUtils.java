package com.lele.locationlibrary;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import static com.baidu.location.b.b.a;

/**
 * Created by lele on 2017/6/12.
 */

public class LocationUtils {
    static LocationUtils locationUtils;
    private Context context;
    LocationClient locationClient;
    private String CoorType=CoordinateType.gcj02;

    public static LocationUtils getInstance() {
        if (locationUtils == null) {
            locationUtils = new LocationUtils();
        }
        return locationUtils;
    }

    /**
     * 初始化SDK，建议在Application中创建
     *
     * @param context
     */
    public void initLocationSDK(Context context) {
        SDKInitializer.initialize(context);
    }

    /**
     * 获取经纬度
     * @param context
     * @param listener
     */
    public void getLocation(Context context, GetLocationListener listener) {
        this.listener = listener;
        locationClient = new LocationClient(context);
        locationClient.registerLocationListener(bdLocationListener);
        locationClient.setLocOption(getLocationClientOption());
        startLocation();

    }

    /**
     * 获取经纬度
     * @param context
     * @param coorType 获取到的坐标类型 CoordinateType
     * @param listener
     */
    public void getLocation(Context context,String coorType, GetLocationListener listener) {
        this.listener = listener;
        this.CoorType=coorType;
        locationClient = new LocationClient(context);
        locationClient.registerLocationListener(bdLocationListener);
        locationClient.setLocOption(getLocationClientOption());
        startLocation();

    }


    /**
     * 开始定位
     */
    public void startLocation() {
        if (locationClient != null) {
            locationClient.start();
        }

    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        if (locationClient != null) {
            locationClient.stop();
        }

    }

    BDLocationListener bdLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int result = bdLocation.getLocType();
            if (result == 161 || result == 61) {
                if (listener != null) {
                    listener.location(bdLocation.getLatitude(), bdLocation.getLongitude());
                }
                stopLocation();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    };
    public GetLocationListener listener;

    public interface GetLocationListener {
        void location(double latitude, double longitude);
    }

    /**
     * @return DefaultLocationClientOption
     */
    public LocationClientOption getLocationClientOption() {
        LocationClientOption mOption = new LocationClientOption();
        mOption = new LocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        mOption.setCoorType(CoorType);//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        mOption.setScanSpan(3000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mOption.setIsNeedAddress(false);//可选，设置是否需要地址信息，默认不需要
        mOption.setIsNeedLocationDescribe(false);//可选，设置是否需要地址描述
        mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
        mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mOption.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mOption.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        return mOption;
    }

    public String getCoorType() {
        return CoorType;
    }
    //设置坐标类型
    public void setCoorType(String coorType) {
        CoorType = coorType;
    }

    class CoordinateType{
        public static final String gcj02="gcj02";//国测局坐标；
        public static final String bd09="bd09"; // 百度墨卡托坐标；
        public static final String bd09ll="bd09ll";//百度经纬度坐标；
    }
}
