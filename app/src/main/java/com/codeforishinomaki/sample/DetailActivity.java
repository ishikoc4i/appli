package com.codeforishinomaki.sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    TextView textView, detailText;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent intent = getIntent();
        String selTimei = intent.getStringExtra("timei");
        int selImage = intent.getIntExtra("image",0);
        final String selLoation = intent.getStringExtra("location");

        textView = (TextView)findViewById(R.id.detailtext);
        textView.setText(selTimei);
        imageView = (ImageView)findViewById(R.id.detailimage);
        imageView.setImageResource(selImage);
        detailText = (TextView)findViewById(R.id.detailtext2);

        button = (Button)findViewById(R.id.mapbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(selLoation)));

            }
        });


    }

}

