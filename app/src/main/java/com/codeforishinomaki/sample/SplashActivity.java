package com.codeforishinomaki.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    int startTime = 1000;       //メインアクティビティが起動するまでの時間

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hdl = new Handler();
        hdl.postDelayed(new splashHandler(), startTime);    //指定した時間遅延させてsplashHandlerを実行
    }

    class splashHandler implements Runnable{
        public void run(){
            //スプラッシュ完了後に実行するAcitivtyを指定する
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            //SplashActivityを終了させる
            SplashActivity.this.finish();
        }
    }
}
