package deepin.leeutil;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import deepin.ctk.CtkApp;

import static android.content.ContentValues.TAG;

/**
 * Created by li on 2017/11/7.
 */

public class UiApp extends Application {
    private static final String TAG = "LeeApplication";
    static Context mContext;
    public Context getContext() {
        return mContext;
    }


    public UiApp() {
        mContext = this;
        CtkApp.getIns().init(this);
    }
    //声明一个监听Activity们生命周期的接口
    private ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        /**
         * application下的每个Activity声明周期改变时，都会触发以下的函数。
         */
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            //如何区别参数中activity代表你写的哪个activity。
            if (activity.getClass() == MainActivity.class)
                Log.i(TAG, activity.getLocalClassName() + "MainActivityCreated.");
            else if(activity.getClass()== TestMeteriaDialogActivity.class)
                Log.i(TAG, activity.getLocalClassName() + "SecondActivityCreated.");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.i(TAG, activity.getLocalClassName() + "onActivityStarted.");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.i(TAG, activity.getLocalClassName() + "onActivityResumed.");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.i(TAG, activity.getLocalClassName() + "onActivityPaused.");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.i(TAG, activity.getLocalClassName() + "onActivityStopped.");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i(TAG, activity.getLocalClassName() + "onActivityDestroyed.");
        }
    };

    /**
     * onCreate是一个回调接口，android系统会在应用程序启动的时候，在任何应用程序组件（activity、服务、
     * 广播接收器和内容提供者）被创建之前调用这个接口。
     * 需要注意的是，这个方法的执行效率会直接影响到启动Activity等的性能，因此此方法应尽快完成。
     * 最后在该方法中，一定要记得调用super.onCreate()，否则应用程序将会报错。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //注册自己的Activity的生命周期回调接口。
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override
    public void onTerminate() {
        //注销这个接口。
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        super.onTerminate();
    }
}
