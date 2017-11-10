package deepin.ctk.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import deepin.ctk.CtkApp;

/**
 * Created by li on 2017/11/7.
 */

public class VoiceUtilImpl extends VoiceUtil {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private boolean mAudioFocus = false;

    @Override
    public VoiceUtil create(int resid) {
        if (mediaPlayer != null) {
            stop();
        }

        mediaPlayer = MediaPlayer.create(CtkApp.getIns().getContext(), resid);
        audioManager = (AudioManager) CtkApp.getIns().getContext().getSystemService(Context.AUDIO_SERVICE);

        // 如果有耳机，则使用听筒默认，就会从耳机输出
        if (isHeadsetExists()) {
            changeToHeadset();
        }

        return this;
    }

    @Override
    public VoiceUtil createLocalVoice( Uri uri) {
        if(uri == null){
            return this;
        }

        if (mediaPlayer != null) {
            stop();
        }


        audioManager = (AudioManager) CtkApp.getIns().getContext().getSystemService(Context.AUDIO_SERVICE);

        try {
            mediaPlayer = MediaPlayer.create(CtkApp.getIns().getContext(), uri);
            if(mediaPlayer == null){
                return this;
            }

            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            return this;
        }

        // 如果有耳机，则使用听筒默认，就会从耳机输出
        if (isHeadsetExists()) {
            changeToHeadset();
        }

        return this;
    }

    @Override
    public VoiceUtil setLooping(boolean looping) {
        if (mediaPlayer == null) {
            return this;
        }

        if (looping) {
            mediaPlayer.setLooping(true);
        } else {
            mediaPlayer.setLooping(false);
        }
        return this;
    }

    @Override
    public VoiceUtil play() {
        if (mediaPlayer == null) {
            return this;
        }

        if(setAudioFocus(true)){
            mAudioFocus = true;
        }

        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("leeTest------>", "setOnCompletionListener");
                if(mAudioFocus){
                    setAudioFocus(false);
                }
            }
        });
        return this;
    }

    @Override
    public VoiceUtil pause() {
        if (mediaPlayer == null) {
            return this;
        }

        if(mAudioFocus){
            setAudioFocus(false);
        }

        mediaPlayer.pause();
        return this;
    }


    @Override
    public VoiceUtil stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();

            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if(mAudioFocus){
            setAudioFocus(false);
        }

        return this;
    }

    @Override
    public boolean isPlaying() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) return true;
        else return false;
    }

    @Override
    public boolean isMusicActive() {
        if (audioManager != null && audioManager.isMusicActive()) return true;
        else return false;
    }

    @Override
    public boolean isHeadsetExists() {
        char[] buffer = new char[1024];
        String HEADSET_STATE_PATH = "/sys/class/switch/h2w/state";
        int newState = 0;

        try {
            FileReader file = new FileReader(HEADSET_STATE_PATH);
            int len = file.read(buffer, 0, 1024);
            newState = Integer.valueOf((new String(buffer, 0, len)).trim());
        } catch (FileNotFoundException e) {
            Log.e("FMTest", "This kernel does not have wired headset support");
        } catch (Exception e) {
            Log.e("FMTest", "", e);
        }
        return newState != 0;
    }

    /**
     * 切换到外放
     */
    public VoiceUtil setSpeakerOpen() {
        if (audioManager == null) {
            return this;
        }

        audioManager.setSpeakerphoneOn(true);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        return this;
    }

    /**
     * 关闭外放
     */
    public VoiceUtil setSpeakerOff() {
        changeToHeadset();
        return this;
    }

    /**
     * 切换到听筒模式
     */
    public VoiceUtil changeToHeadset() {
        if (audioManager == null) {
            return this;
        }

        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }

        return this;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public boolean setAudioFocus(boolean bFocus) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            // 2.1以下的版本不支持下面的API：requestAudioFocus和abandonAudioFocus
            Log.d("ANDROID_LAB", "Android 2.1 and below can not stop music");
            return false;
        }

        boolean bool = false;
        AudioManager am = (AudioManager) CtkApp.getIns().getContext().getSystemService(Context.AUDIO_SERVICE);
        if (bFocus) {
            int result = am.requestAudioFocus(null, AudioManager.MODE_IN_COMMUNICATION, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        } else {
            int result = am.abandonAudioFocus(null);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        }

        Log.i("leeTest------->", "pauseMusic bMute = " + bFocus + " result = " + bool);
        return bool;
    }

    public void destory() {
        if (audioManager != null) {
            audioManager.setMode(AudioManager.MODE_NORMAL);
        }

        if(mAudioFocus){
            setAudioFocus(false);
        }

        super.destory();
    }
}
