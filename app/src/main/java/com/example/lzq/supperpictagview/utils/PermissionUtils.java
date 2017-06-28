package com.example.lzq.supperpictagview.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by lzq on 2016/6/30.
 */
public class PermissionUtils {
    static Activity context;

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE=200;
    /**
     * 单例对象实例
     */
    private static PermissionUtils instance = null;

    public static PermissionUtils getInstance(Activity context) {
        if (instance == null) {
            instance = new PermissionUtils(context);
        }
        return instance;
    }

    private PermissionUtils(Activity context) {
        this.context=context;
    }

    public  void needPermission(int requestCode)
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        requestAllPermissions(requestCode);
    }
    /*
    *
    * 申请授予权限
    * CALL_PHONE  READ_EXTERNAL_STORAGE CAMERA  READ_CONTACTS GET_ACCOUNTS ACCESS_FINE_LOCATION  Manifest.permission.RECORD_AUDIO
    * */
    public  void requestAllPermissions( int requestCode)
    {

            ActivityCompat.requestPermissions(context,
                    new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

    }
    /*
    * 打电话
    * */
    public  boolean requesCallPhonePermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CALL_PHONE},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    public  boolean requesPhoneStatePermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    /*
    * 通讯录
    * */
    public  boolean requesREAD_CONTACTS( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }

    public  boolean requestReadSDCardPermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    public  boolean requestCamerPermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.CAMERA},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    public  boolean requestReadConstantPermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    public  boolean requestGET_ACCOUNTSPermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.GET_ACCOUNTS},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
    public  boolean requestLocationPermissions( int requestCode)
    {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {//没有权限
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    requestCode);
            return false;
        } else
        {
            return true;
        }
    }
}
