package deepin.leeutil;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import deepin.ctk.media.MainService;
import deepin.ctk.media.VoiceUtil;

public class TestVoiceActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_voice);

        findViewById(R.id.id_create).setOnClickListener(this);
        findViewById(R.id.id_setLoop).setOnClickListener(this);
        findViewById(R.id.id_play).setOnClickListener(this);
        findViewById(R.id.id_speaker).setOnClickListener(this);
        findViewById(R.id.id_close_speaker).setOnClickListener(this);
        findViewById(R.id.id_destory).setOnClickListener(this);
        findViewById(R.id.id_pause).setOnClickListener(this);
        findViewById(R.id.id_check_has_music).setOnClickListener(this);
        findViewById(R.id.id_focus).setOnClickListener(this);
        findViewById(R.id.id_cancel_focus).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_create:
                onClickCreate();
                break;
            case R.id.id_setLoop:
                onClickLoop();
                break;
            case R.id.id_play:
                onClickPlay();
                break;
            case R.id.id_speaker:
                onClickSpeaker();
                break;
            case R.id.id_close_speaker:
                onClickCloseSpeaker();
                break;
            case R.id.id_pause:
                onClickPause();
                break;

            case R.id.id_destory:
                onClickDestory();
                break;
            case R.id.id_check_has_music:
                onClickHasMusic();
                break;
            case R.id.id_focus:
                onClickHasFocus(true);
                break;
            case R.id.id_cancel_focus:
                onClickHasFocus(false);
                break;
            default:
                break;
        }
    }

    private void onClickHasFocus(boolean focus) {
        VoiceUtil.getIns().setAudioFocus(focus);

//        if(focus){
//            startService(new Intent(this, MainService.class));
//        }else {
//            stopService(new Intent(this, MainService.class));
//        }
    }

    private void onClickHasMusic() {
        boolean bMusicActive = VoiceUtil.getIns().isMusicActive();
        Toast.makeText(this,bMusicActive ? "有音乐正在播放": "无音乐" , Toast.LENGTH_LONG).show();
    }

    private void onClickPause() {
        VoiceUtil.getIns().pause();
    }


    private void onClickCreate() {
//        VoiceUtil.getIns().create(R.raw.call_ring);
        String path = Environment.getDataDirectory().getPath();
        VoiceUtil.getIns().createLocalVoice(Uri.parse("/data/Honor.mp3"));
    }

    private void onClickLoop() {
        VoiceUtil.getIns().setLooping(true);
    }

    private void onClickPlay() {
        VoiceUtil.getIns().play();
    }

    private void onClickSpeaker() {
        VoiceUtil.getIns().setSpeakerOpen();
    }

    private void onClickCloseSpeaker() {
        VoiceUtil.getIns().setSpeakerOff();

    }

    private void onClickDestory() {
        VoiceUtil.getIns().destory();
    }

}
