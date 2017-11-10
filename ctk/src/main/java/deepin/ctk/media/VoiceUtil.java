package deepin.ctk.media;

import android.net.Uri;

/**
 * Created by li on 2017/11/7.
 */

public class VoiceUtil {
    private static VoiceUtil ins;
    protected boolean mIsUsing = false;

    /**
     * 对外方法区域
     */
    public VoiceUtil create( int resid){return this;}

    /**
     * 播放本地音乐
     * @param uri eg: Uri.parse("file://mnt/sdcard/Music/demo.mp3")
     */
    public VoiceUtil createLocalVoice( Uri uri){return this;}

    /**
     * 播放网络音乐
     * @param uri eg: Uri.parse("http://192.168.1.12/internet.mp3")
     */
    public VoiceUtil createInternetVoice( Uri uri){return this;}

    public VoiceUtil play(){return this;}

    public VoiceUtil stop(){return this;}
    public VoiceUtil pause(){return this;}
    public VoiceUtil setLooping(boolean bloop){return this;}

    public VoiceUtil setSpeakerOpen(){return this;}
    public VoiceUtil setSpeakerOff(){return this;}
    public VoiceUtil changeToHeadset(){return this;}

    public boolean setAudioFocus(boolean bFocus){return false;};

//    public void removeHeadset(){}
//    public void changeToReceiver(){}

    public boolean isPlaying(){return false;}
    public boolean isHeadsetExists(){return false;}
    public boolean isMusicActive() {return false;}
    // --------------------------------------------------------------
    public void destory() {
        if (isPlaying()) {
            stop();
        }

        mIsUsing = false;
        ins = null;
    }

    public boolean isUsing() {
        return mIsUsing;
    }

    public static VoiceUtil getIns() {
        if(ins == null){
            synchronized (VoiceUtil.class){
                if(ins == null){
                    ins = new VoiceUtilImpl();
                }
            }
        }
        return ins;
    }


}
