
import { NativeModules,PermissionsAndroid } from 'react-native';

const { RNYunBaiduloc } = NativeModules;

export default class BaiduLoc{

    static getLocation(success,fail) {
        PermissionsAndroid.check(PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION).then(granted=>{
            if (!granted) {
                PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION).then(result=>{
                    if (result === PermissionsAndroid.RESULTS.GRANTED) {
                        RNYunBaiduloc.start(true,callback);
                    }else if (result === PermissionsAndroid.RESULTS.NEVER_ASK_AGAIN) {
                        // 不再询问
                        if (typeof fail === 'function') {
                            fail('never_ask_again');
                        }else{
                            alert("定位失败，没有权限")
                        }
                    }else{
                        // 被拒绝
                        if (typeof fail === 'function') {
                            fail('denied');
                        }else{
                            alert("定位失败，没有权限")
                        }
                    }
                })
            }else{
                RNYunBaiduloc.start(true,callback);
            }
        })
    }
}
