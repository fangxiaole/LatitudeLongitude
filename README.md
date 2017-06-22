[![](https://jitpack.io/v/fangxiaole/LatitudeLongitude.svg)](https://jitpack.io/#fangxiaole/LatitudeLongitude)
# LatitudeLongitude
### 引用
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}Copy
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.fangxiaole:LatitudeLongitude:v1.1'
	}
 ### 使用说明：
  1.初始化百度定位SDK（最好在Application中)
  ```Java
  LocationUtils.getInstance().initLocationSDK(getApplicationContext());
  ```
  2.获取定位经纬度
  ```Java
  LocationUtils.getInstance().getLocation(getApplicationContext(), new LocationUtils.GetLocationListener() {
                    @Override
                    public void location(double latitude, double longitude) {
                        //latitude:经度 longitude：纬度
                    }
  });   
  ```
  2.两个位置之间的距离是否在指定范围内      
  ```Java
  LatLng latLng1=new LatLng(22.615255,114.035641);//位置1
  LatLng latLng2=new LatLng(22.608449,114.126047);//位置2
  DistanceUtils.isInDistance(latLng1,latLng2,200);//返回是否在200米以内
  ```
