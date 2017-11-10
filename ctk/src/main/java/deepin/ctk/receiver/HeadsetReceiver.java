package deepin.ctk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import deepin.ctk.media.VoiceUtil;

/**
 * Created by li on 2017/11/7.
 */

public class HeadsetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            //插入和拔出耳机会触发此广播
            case Intent.ACTION_HEADSET_PLUG:
                if(!VoiceUtil.getIns().isUsing()){
                    VoiceUtil.getIns().destory();
                    return;
                }

                int state = intent.getIntExtra("state", 0);
                if (state == 1){
                    VoiceUtil.getIns().changeToHeadset();
                } else if (state == 0){
                    VoiceUtil.getIns().setSpeakerOpen();
                }
                break;
            default:
                break;
        }
    }
}
