package com.lele.user.latitudelongitude;

/**
 * Created by lele on 2017/7/7.
 */

public class DeviceLocation {
    //经度
    private double latitude;
    //纬度
    private double longitude;
    //设备ID
    private String deviceId;

    public DeviceLocation() {
    }

    public DeviceLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DeviceLocation(double latitude, double longitude, String deviceId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.deviceId = deviceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取存储字符串
     * @return 经度|纬度
     *
     */
    public String getSpString(){
        return latitude+"|"+longitude;
    }

    @Override
    public String toString() {
        return "DeviceLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
