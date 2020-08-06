
package com.hyunsu.reactnative.baiduloc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.HashMap;

public class RNYunBaidulocModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNYunBaidulocModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        onApplicationCreate(reactContext);
    }

    @Override
    public String getName() {
        return "RNYunBaiduloc";
    }


    @ReactMethod
    public void test(Callback callback) {
        callback.invoke("aaabbb");
    }

    public static LocationClient mLocationClient = null;

    // 是否单次定位
    public static boolean singleMode = true;
    // react native 传入的回调函数
    public static Callback reactCallback;

    @ReactMethod
    public void start(boolean single, Callback callback) {
        singleMode = single;
        reactCallback = callback;
        mLocationClient.start();
    }

    @ReactMethod
    public void reStart() {
        mLocationClient.restart();
    }

    public  void onApplicationCreate(Context context) {
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(new LocationListener());

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setIsNeedAddress(true);

        option.setIsNeedAltitude(true);

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(0);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setNeedNewVersionRgc(true);
//可选，设置是否需要最新版本的地址信息。默认需要，即参数为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

}