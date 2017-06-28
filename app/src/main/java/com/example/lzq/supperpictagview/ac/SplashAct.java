package com.example.lzq.supperpictagview.ac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.example.lzq.supperpictagview.MainActivity;
import com.example.lzq.supperpictagview.R;
import com.example.lzq.supperpictagview.utils.PermissionUtils;

import java.io.File;


/**
 * 欢迎界面
 **/
public class SplashAct extends Activity {
    public static int total=0;
    private Context context;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE=200;

    //声明AMapLocationClient类对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=this;
        if (!isTaskRoot()) {
            finish();
            return;
        }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                PermissionUtils.getInstance(this).needPermission(200);
            }else {
                // 如果是6.0以下 不需要权限判断检查版本
                startActivity(new Intent(context,MainActivity.class));
            }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    for (int i=0;i<grantResults.length;i++){
                        if (grantResults[i]== PackageManager.PERMISSION_GRANTED){
                            total++;//同意权限个数+1
                        }
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Log.i("用户同意权限", "user granted the permission!");
                    if (total==grantResults.length){//证明用户已经同意所有权限请求
                        // 检查版本
//                        checkVersion();
//                        initBaiduMap();
                        startActivity(new Intent(context,MainActivity.class));
                    }else {
                        Toast.makeText(SplashAct.this,"亲没有以上权限，无法进去应用哦", Toast.LENGTH_SHORT).show();
                        total=0;
                        PermissionUtils.getInstance(this).needPermission(200);
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Log.i("用户不同意权限", "user denied the permission!");
                    }
                }
                return;
            }

        }
    }



    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();

    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {

        super.onPause();
    }




}
