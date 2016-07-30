package com.codeforishinomaki.sample;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.FlakyTest;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.vV);

        videoView.setVideoPath("R.raw.movie.mp4");

        videoView.setVideoURI(Uri.parse("android.resource://" +
                this.getPackageName() + "/" + R.raw.movie));

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    }else{
                        videoView.start();
                    }
                }

                return true;
            }
        });

        //videoView.setMediaController(new MediaController(this));



    }

    @Override
    protected void onResume() {
        super.onResume();

        videoView.start();

    }
}
