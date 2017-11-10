package deepin.ctk;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;

import deepin.ctk.media.EarPhoneReceiver;

/**
 * Created by li on 2017/11/7.
 */

public class CtkApp extends Application {
    Context mContext;
    private static CtkApp ins;
    EarPhoneReceiver earPhoneReceiver;

    public Context getContext() {
        return mContext;
    }

    // 单例，并且需要先初始化 -----------------------------------------------
    public boolean init(Context context) {
        mContext = context;

        registerEarPhoneReceiver();
        return false;
    }

    public static CtkApp getIns() {
        if (ins == null) {
            synchronized (CtkApp.class) {
                if (ins == null) {
                    ins = new CtkApp();
                }
            }
        }
        return ins;
    }

    public CtkApp() {
        mContext = this;
    }

    // --------------------------------------------------------

    private void registerEarPhoneReceiver(){
//        earPhoneReceiver = new EarPhoneReceiver();
//        IntentFilter filter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
//        //       filter.addAction("android.intent.action.HEADSET_PLUG");
//        mContext.registerReceiver(earPhoneReceiver,filter);
    }

}
