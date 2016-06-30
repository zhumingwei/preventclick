package cn.zhichi.preventclick.application;

import android.app.Application;

/**
 * Created by zhumingwei on 16/6/30.
 */
public class CommonApp extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static Application getInstance() {
        return instance;
    }


}
