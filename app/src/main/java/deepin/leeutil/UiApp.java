package deepin.leeutil;

import android.app.Application;
import android.content.Context;

import deepin.ctk.CtkApp;

/**
 * Created by li on 2017/11/7.
 */

public class UiApp extends Application {

    static Context mContext;
    public Context getContext() {
        return mContext;
    }


    public UiApp() {
        mContext = this;
        CtkApp.getIns().init(this);
    }

}
