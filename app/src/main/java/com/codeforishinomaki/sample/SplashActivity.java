package com.codeforishinomaki.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    //次は壁紙を表示するために、重ねるようにxmlファイルを編集する
    //randomのほうも終了したら、終了するようにする

    int startTime = 2000;       //メインアクティビティが起動するまでの時間
    //int randomNumber = 8;                //ランダムでイメージを変えるやつの変数   8の場合：0～7

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //randomImage a_1 = new randomImage();        //randomImageクラスをインスタンス化【a_1】
        //a_1.method();                               //メソッド呼び出し

        
        Handler hdl = new Handler();
        hdl.postDelayed(new splashHandler(), startTime);    //指定した時間遅延させてsplashHandlerを実行
    }

    //   class randomImage{
//        void method(){
//            Random rnd = new Random();         //Randomクラスのインスタンス化
//            int ran = rnd.nextInt(randomNumber);
//            System.out.println("random変数"+ran);
//        }
//    }

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
