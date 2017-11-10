package deepin.ctk.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

/**
 * Created by li on 2017/11/7.
 */
public class EarPhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)){
        if (intent.getAction().equals(AudioManager.ACTION_AUDIO_BECOMING_NOISY)){
            //处理事件
            Log.i("leeTest------>", "");
        }

    }
}
