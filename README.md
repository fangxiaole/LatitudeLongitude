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
  1.初始化百度定位SDK（最好在Application中）  
  ```
   LocationUtils.getInstance().initLocationSDK(getApplicationContext());
  ```     
  2.获取定位经纬度
  ```
  LocationUtils.getInstance().getLocation(getApplicationContext(), new LocationUtils.GetLocationListener() {
                    @Override
                    public void location(double latitude, double longitude) {
                        //latitude:经度 longitude：纬度
                    }
  });   
  ```
