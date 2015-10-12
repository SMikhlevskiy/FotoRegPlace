package smikhlevskiy.myfirstapplication.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;


/**
 * Created by tcont98 on 11-Oct-15.
 */
public class ServicePlayMusic extends Service implements MediaPlayer.OnPreparedListener {
    public static final String PLAY_HTTP_MUSIC = "PlayHTTPMusic";
    //private static final String URI_KEY = "UriKey";

    MediaPlayer mMediaPlayer = null;
    //private BroadcastReceiver broadcastReceiver;


    public int onStartCommand(Intent intent, int flags, int startId) {
        //...
        Log.i(this.getClass().getName(), "onStartCommand");
        /*
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(this.getClass().getName(),"MusicPlay");
                final String DATA_HTTP = "http://dl.dropboxusercontent.com/u/6197740/explosion.mp3";

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(DATA_HTTP);
                } catch (IOException e) {

                    e.printStackTrace();

                }
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                mediaPlayer.setOnPreparedListener(ServicePlayMusic.this);
                mediaPlayer.prepareAsync();

            }
        };

        IntentFilter intentFilter = new IntentFilter(PLAY_HTTP_MUSIC);
        registerReceiver(broadcastReceiver, intentFilter);
        */

        Log.i(this.getClass().getName(),"MusicPlay");
        final String DATA_HTTP = "http://dl.dropboxusercontent.com/u/6197740/explosion.mp3";

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(DATA_HTTP);
        } catch (IOException e) {

            e.printStackTrace();

        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(ServicePlayMusic.this);
        mediaPlayer.prepareAsync();



        return 0;
    }

    @Override
    public void onDestroy() {
        Log.i(this.getClass().getName(),"DestroyService");
        //unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Called when MediaPlayer is ready
     */
    public void onPrepared(MediaPlayer player) {
        player.start();
    }
}