package com.lele.locationlibrary;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

/**
 * Created by lele on 2017/6/12.
 */

public class DistanceUtils {
    /**
     * 是否在指定范围内
     * @param start 开始经纬度坐标
     * @param end  结束经纬度坐标
     * @param distance 指定距离（以米为单位）
     * @return 两个位置之间的距离是否在指定距离之内
     */
    public static boolean isInDistance(LatLng start, LatLng end, double distance){
        return DistanceUtil.getDistance(start,end)<distance;
    }
}
