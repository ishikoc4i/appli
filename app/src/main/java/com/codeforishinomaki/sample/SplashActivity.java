package com.codeforishinomaki.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class SplashActivity extends Activity {

    int startTime = 1000;       //メインアクティビティが起動するまでの時間

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        randomImage();                  //ランダムに背景画像を決定するやつ実行

        Handler hdl = new Handler();
        hdl.postDelayed(new splashHandler(), startTime);    //指定した時間遅延させてsplashHandlerを実行
    }

    void randomImage(){      //ランダムに背景画像を決定するやつ実行
        int [] images = {
                R.drawable.sp_pic1,
                R.drawable.sp_pic2,
                R.drawable.sp_pic3,
                R.drawable.sp_pic4,
                R.drawable.sp_pic5,
                R.drawable.sp_pic6,
                R.drawable.sp_pic7};

        Random rnd = new Random();
        int ran = rnd.nextInt(images.length);
        int imageid = images[ran];

        ImageView view = (ImageView)this.findViewById(R.id.image01);
        view.setImageResource(imageid);
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
