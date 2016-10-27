package com.codeforishinomaki.sample;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewItemClickListener {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLayouts();
    }

    public void setLayouts() {
        // RecyclerViewを定義する
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // RecyclerViewの表示の設定
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        // アイテムを表示させる情報をもつアダプターを生成
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Constants.locations, this);

        // アダプターをRecyclerViewにセット
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * RecyclerViewのアイテムがクリックされた時に呼び出される
     * @param clickedView クリックされたView
     * @param position クリックされたアイテムの位置
     */
    @Override
    public void onItemSelected(View clickedView, int position) {
        //TODO RecyclerViewのアイテムがクリックされた時の処理をここに書く
    }
}
