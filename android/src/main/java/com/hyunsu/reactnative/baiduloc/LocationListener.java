package com.hyunsu.reactnative.baiduloc;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;

import java.util.HashMap;

public class LocationListener extends BDAbstractLocationListener {


    @Override
    public void onReceiveLocation(BDLocation location) {
        if (RNYunBaidulocModule.singleMode) {
            RNYunBaidulocModule.mLocationClient.stop();
        }

        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f
        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
        int locType = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        String adCode = location.getAdCode();
        String addrStr = location.getAddrStr();
        String cityCode = location.getCityCode();
        String city = location.getCity();
        String country = location.getCountry();
        String district = location.getDistrict();
        String locationDescribe = location.getLocationDescribe();
        String locationId = location.getLocationID();
        String province = location.getProvince();
        double altitude = location.getAltitude();

        HashMap<String, Object> result = new HashMap<>();
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("radius", radius);
        result.put("coorType", coorType);
        result.put("adCode", adCode);
        result.put("addrStr", addrStr);
        result.put("cityCode", cityCode);
        result.put("city", city);
        result.put("country", country);
        result.put("district", district);
        result.put("locationDescribe", locationDescribe);
        result.put("locationId", locationId);
        result.put("province", province);
        result.put("altitude", altitude);


        RNYunBaidulocModule.reactCallback.invoke(new Gson().toJson(result));

    }
}
