package com.codeforishinomaki.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    @Override
    protected void onResume() {
        super.onResume();

    }


    /**
     * RecyclerViewのアイテムがクリックされた時に呼び出される
     * @param clickedView クリックされたView
     * @param position クリックされたアイテムの位置
     */
    public void onItemSelected(View clickedView, int position) {
        // RecyclerViewのアイテムがクリックされた時の処理をここに書く
        //TODO positionを使ってConstantsのlocationsから、position番目のアイテム情報を取得
        LocationInfo info = Constants.locations[position];
        String timei = info.getTimei();
        int pos = info.pos();
        String location = info.getLocation();
        int  imageResourceId = info.getImageResourceId();
        String address = info.getAddress();
        
        //TODO 取り出したlocationsを使って、下のような感じでIntentに選択された情報を入力していく
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("timei", timei);
        intent.putExtra("pos", pos);
        intent.putExtra("image", imageResourceId);
        intent.putExtra("location", location);
        intent.putExtra("address", address);
        startActivity(intent);
        //TODO 最後にStartActivityで詳細のアクティビティに遷移を指せる
    }


}
