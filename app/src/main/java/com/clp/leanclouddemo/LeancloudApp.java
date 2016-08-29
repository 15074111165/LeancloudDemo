package com.clp.leanclouddemo;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import java.util.Properties;

/**
 * Created by chenliupushishabi on 2016/8/24.
 */
public class LeancloudApp extends Application {

    private static Context mContext;
    public static String installationId;

    public static boolean isRevice=true;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        initLeancloud();
        Properties properties=null;
        if(BuildConfig.DEBUG){
            properties=getDebugProperties();
        }else{
            properties=getReleaseProperties();
        }

        AppProperties.show= (String) properties.get("show");

    }


    public Properties getReleaseProperties(){
        return getProperties("release.properties");
    }


    public Properties getDebugProperties(){
        return getProperties("debug.properties");
    }


    private  Properties getProperties(String propertiesName) {
        String url = null;
        Properties properties = new Properties();
        try {
            properties.load(getAssets().open(propertiesName));
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void  initLeancloud(){
        AVOSCloud.initialize(mContext, "WGsQgF1DM60vHiLnC4t9QOUg-gzGzoHsz", "drJuObsVv22fCk77sKrpmLHK");



        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());

        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {//保存成功
                     installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                    // 关联  installationId 到用户表等操作…… 当前用户userId与instalolactionId绑定
                        AVIMClient.getInstance(installationId).open(new AVIMClientCallback() {
                        @Override
                        public void done(AVIMClient avimClient, AVIMException e) {

                        }
                    });
                } else {
                    // 保存失败，输出错误信息
                }
            }
        });

        // 设置默认打开的 Activity
        PushService.setDefaultPushCallback(mContext, MainActivity.class);
    }

    public static void unSubScribe(){

        AVIMClient.getInstance(installationId).open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {

            }
        });
    }
}
