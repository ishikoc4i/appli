package com.codeforishinomaki.sample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.sql.Time;

public class DetailActivity extends AppCompatActivity {

    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;

    float posX;

    TextView PlaceName;
    ViewFlipper viewFlipper;
    ImageView RightButton, LeftButton;
    ImageView pic1, pic2, pic3, pic4, pic5, pic6;
    ImageView BackAllow;
    ImageView Point1, Point2;
    ImageView Map;

    String timei;
    String location;
    int imageResorceId;
    int pos;


    int pointdecision = 0;

    Animation inFromRightAnimation;
    Animation inFromLeftAnimation;
    Animation outToRightAnimation;
    Animation outToLeftAnimation;
    int TimeiInt;
    String PanoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        panoWidgetView = (VrPanoramaView) findViewById(R.id.vr_view);

        Intent intent = getIntent();
        timei = intent.getStringExtra("timei");
        location = intent.getStringExtra("location");
        imageResorceId = intent.getIntExtra("image", 0);
        pos = intent.getIntExtra("pos", 0);
        loadPanoImage();

        BackAllow = (ImageView)findViewById(R.id.backallow);

        BackAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);

            }
        });

        PlaceName = (TextView) findViewById(R.id.placename);
        PlaceName.setText(timei);

        viewFlipper = (ViewFlipper)findViewById(R.id.flipper);

        RightButton = (ImageView)findViewById(R.id.right_button);
        LeftButton = (ImageView)findViewById(R.id.left_button);

        pic1 = (ImageView)findViewById(R.id.pic_item1);
        pic2 = (ImageView)findViewById(R.id.pic_item2);
        pic3 = (ImageView)findViewById(R.id.pic_item3);
        pic4 = (ImageView)findViewById(R.id.pic_item4);
        pic5 = (ImageView)findViewById(R.id.pic_item5);
        pic6 = (ImageView)findViewById(R.id.pic_item6);

        pic1.setImageResource(R.drawable.picture1);
        pic2.setImageResource(R.drawable.picture2);
        pic3.setImageResource(R.drawable.picture3);
        pic4.setImageResource(R.drawable.picture4);
        pic5.setImageResource(R.drawable.picture5);
        pic6.setImageResource(R.drawable.picture6);

        Point1 = (ImageView)findViewById(R.id.point1);
        Point2 = (ImageView)findViewById(R.id.point2);
        Point1.setImageResource(R.drawable.point_light);
        Point2.setImageResource(R.drawable.point_dark);

        PointDarkLight();

        Map = (ImageView) findViewById(R.id.mapIcon);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location)));
            }
        });

        inFromRightAnimation =
                AnimationUtils.loadAnimation(this, R.anim.right_in);
        inFromLeftAnimation =
                AnimationUtils.loadAnimation(this, R.anim.left_in);
        outToRightAnimation =
                AnimationUtils.loadAnimation(this, R.anim.right_out);
        outToLeftAnimation =
                AnimationUtils.loadAnimation(this, R.anim.left_out);

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        posX = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (posX > event.getX()){
                            viewFlipper.setInAnimation(inFromRightAnimation);
                            viewFlipper.setOutAnimation(outToLeftAnimation);
                            viewFlipper.showNext();
                            if (pointdecision == 0){
                                pointdecision = 1;
                            }else if (pointdecision == 1){
                                pointdecision = 0;
                            }
                            PointDarkLight();

                        }else if (posX < event.getX()){
                            viewFlipper.setInAnimation(inFromLeftAnimation);
                            viewFlipper.setOutAnimation(outToRightAnimation);
                            viewFlipper.showPrevious();
                            if (pointdecision == 0){
                                pointdecision = 1;
                            }else if (pointdecision == 1){
                                pointdecision = 0;
                            }
                            PointDarkLight();

                        }
                    default:
                        break;
                }
                return true;
            }
        });

        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(inFromRightAnimation);
                viewFlipper.setOutAnimation(outToLeftAnimation);
                viewFlipper.showNext();
                if (pointdecision == 0){
                    pointdecision = 1;
                }else if (pointdecision == 1){
                    pointdecision = 0;
                }
                PointDarkLight();

            }
        });

        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setInAnimation(inFromLeftAnimation);
                viewFlipper.setOutAnimation(outToRightAnimation);
                viewFlipper.showPrevious();
                if (pointdecision == 0){
                    pointdecision = 1;
                }else if (pointdecision == 1){
                    pointdecision = 0;
                }
                PointDarkLight();
            }
        });

    }

    private void PointDarkLight(){
        if (pointdecision == 0){
            Point1.setImageResource(R.drawable.point_light);
            Point2.setImageResource(R.drawable.point_dark);
        }else if (pointdecision == 1){
            Point1.setImageResource(R.drawable.point_dark);
            Point2.setImageResource(R.drawable.point_light);
        }
    }

    @Override
    public void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        panoWidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown();
        super.onDestroy();
    }

    String panoImageName;

    private synchronized void loadPanoImage() {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_MONO;

        // use the name of the image in the assets/ directory.

        if (pos == 1){
            panoImageName = "mountain.jpg";
        }else if (pos == 2){
            panoImageName = "image3601.jpg";
        }else if (pos == 3){
            panoImageName = "image3602.jpg";
        }else if (pos == 4){
            panoImageName = "image3603.jpg";
        }else if (pos == 5){
            panoImageName = "image3604.jpg";
        }

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panoWidgetView, viewOptions, panoImageName);
        task.execute(getAssets());
        backgroundImageLoaderTask = task;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
    }
}
