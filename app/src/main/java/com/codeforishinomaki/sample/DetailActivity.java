package com.codeforishinomaki.sample;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

public class DetailActivity extends AppCompatActivity {

    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;

    float posX;

    TextView AddressText;
    ViewFlipper viewFlipper;
    ImageView RightButton, LeftButton;
    ImageView pic1, pic2, pic3, pic4, pic5, pic6;
    ImageView Point1, Point2;
    ImageView Map;

    String timei;
    String location;
    int imageResorceId;
    int pos;
    String address;
    String VRImage;

    int pointdecision = 0;

    Animation inFromRightAnimation;
    Animation inFromLeftAnimation;
    Animation outToRightAnimation;
    Animation outToLeftAnimation;

    int image1, image2, image3, image4, image5, image6;
    int winW;
    int previewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        panoWidgetView = (VrPanoramaView) findViewById(R.id.vr_view);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        timei = intent.getStringExtra("timei");
        location = intent.getStringExtra("location");
        imageResorceId = intent.getIntExtra("image", 0);
        address = intent.getStringExtra("address");
        VRImage = intent.getStringExtra("VRimage");

        image1 = intent.getIntExtra("detailImage1", 0);
        image2 = intent.getIntExtra("detailImage2", 0);
        image3 = intent.getIntExtra("detailImage3", 0);
        image4 = intent.getIntExtra("detailImage4", 0);
        image5 = intent.getIntExtra("detailImage5", 0);
        image6 = intent.getIntExtra("detailImage6", 0);

        viewFlipper = (ViewFlipper)findViewById(R.id.flipper);
        RightButton = (ImageView)findViewById(R.id.right_button);
        LeftButton = (ImageView)findViewById(R.id.left_button);

        pic1 = (ImageView)findViewById(R.id.pic_item1);
        pic2 = (ImageView)findViewById(R.id.pic_item2);
        pic3 = (ImageView)findViewById(R.id.pic_item3);
        pic4 = (ImageView)findViewById(R.id.pic_item4);
        pic5 = (ImageView)findViewById(R.id.pic_item5);
        pic6 = (ImageView)findViewById(R.id.pic_item6);

        pic1.setImageResource(image1);
        pic2.setImageResource(image2);
        pic3.setImageResource(image3);
        pic4.setImageResource(image4);
        pic5.setImageResource(image5);
        pic6.setImageResource(image6);

        Point1 = (ImageView)findViewById(R.id.point1);
        Point2 = (ImageView)findViewById(R.id.point2);
        Point1.setImageResource(R.drawable.point_light);
        Point2.setImageResource(R.drawable.point_dark);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        winW = dm.widthPixels;
        System.out.println("winW:" + winW);

        PointDarkLight();
        //タイトルバーの名前が変更できる
        setTitle(timei);

        // アクションバーに前画面に戻る機能をつける
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        Map = (ImageView) findViewById(R.id.mapIcon);

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location)));
            }
        });

        AddressText = (TextView) findViewById(R.id.addressText);
        AddressText.setText(address);

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
                        System.out.println("posX :" + posX);
                        break;

                    case MotionEvent.ACTION_UP:
                        if (posX > event.getX() + 20){
                            viewFlipper.setInAnimation(inFromRightAnimation);
                            viewFlipper.setOutAnimation(outToLeftAnimation);
                            viewFlipper.showNext();
                            if (pointdecision == 0){
                                pointdecision = 1;
                            }else if (pointdecision == 1){
                                pointdecision = 0;
                            }
                            PointDarkLight();

                        }else if (posX < event.getX() - 20){
                            viewFlipper.setInAnimation(inFromLeftAnimation);
                            viewFlipper.setOutAnimation(outToRightAnimation);
                            viewFlipper.showPrevious();
                            if (pointdecision == 0){
                                pointdecision = 1;
                            }else if (pointdecision == 1){
                                pointdecision = 0;
                            }
                            PointDarkLight();

                        }else{

                            int winW13 = winW * 1/3;
                            int winW23 = winW * 2/3;


                            if (posX < winW13){
                                if (pointdecision == 0){
                                    previewImage = image1;
                                }else{
                                    previewImage = image4;
                                }
                            }else if (posX < winW23){
                                if (pointdecision == 0){
                                    previewImage = image2;
                                }else{
                                    previewImage = image5;
                                }
                            }else if (winW23 < posX){
                                if (pointdecision == 0){
                                    previewImage = image3;
                                }else{
                                    previewImage = image6;
                                }
                            }

                            imagePreview();

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
        loadPanoImage();
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

    private void imagePreview(){
        ImageView iv = new ImageView(DetailActivity.this);
        iv.setImageResource(previewImage);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setAdjustViewBounds(true);
        Dialog dialog = new Dialog(DetailActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(iv);
        dialog.show();
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
        panoImageName = VRImage;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
